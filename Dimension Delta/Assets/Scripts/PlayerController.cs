﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {

    public float force = 15;
    public float jump = 50;
    Collider2D c2D;
    Rigidbody2D rb;
    Animator animator;
    SpriteRenderer sr;
    public RectTransform hpBar;
    public Text hpString;
    public float hp;
    public int maxhp;
    public float PixperHP;
    int struggle = 0;
    bool preJump;
    bool preMove;
    bool slimed;
 
    void Start () {
        animator = GetComponent<Animator>();
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
        c2D = GetComponent<Collider2D>();
        maxhp = 20;
        hp = maxhp;
        PixperHP = 70 / maxhp;
        //rb.constraints = RigidbodyConstraints2D.None;
    }
	
	void FixedUpdate () {

        //** MOVEMENT **//

        if (!slimed) rb.AddForce(new Vector2(Input.GetAxis("Horizontal") * force, (float).2)); //get input from keyboard/controller
        //Good speed from 23 force, coming to a quick stop is thanks to the 2 linear drag on rigid body and 3 grav

        if (rb.velocity.x > 0 && rb.constraints != RigidbodyConstraints2D.FreezePositionX) //Does the player face right?
        {
            if(!slimed)sr.flipX = false;
        }
        else if(rb.velocity.x < 0 && rb.constraints != RigidbodyConstraints2D.FreezePositionX) //Or are they facing left?
        {
            if (!slimed) sr.flipX = true;
        }
        if (Input.GetAxis("Horizontal") != 0) //If there is directional input
        {
            if (!slimed)
            {
                animator.SetInteger("speed", 1);
            }
            if(slimed)
            {
                if (!preMove) struggle++;
                preMove = true;
                animator.SetBool("strR?", true);
            }
        }
        else // No input
        {
            animator.SetInteger("speed", 0);//Reroutes loop to idle
            preMove = false;
            animator.SetBool("strR?", false);
        }
        if (Input.GetAxis("Jump") > 0 && animator.GetBool("Grounded") == true) //Need to press jump and be on the ground to jump
        {
            animator.SetBool("Jumping", true); //Jump squat
            if (!slimed)
            {
                rb.AddForce(Vector2.up * jump);
                animator.SetBool("Grounded", false);
            }
            else{
                if (!preJump)
                {
                    struggle++;
                }
            }
            preJump = true;
        }
        if (animator.GetBool("Grounded") == true)
        {
            animator.SetBool("Jumping", false);
        }

        if(Input.GetAxis("Jump") == 0)
        {
            preJump = false;
        }

        if (struggle > 10)
        {
            animator.SetBool("Slimed", false);
        }
        slimed = animator.GetBool("Slimed");

        if (Input.GetKeyDown("d"))
        {
            Debug.Log("You have taken damage!!");
            hp -= 1;
        }

        //** HEALTH MECHANICS **//

        hpBar.sizeDelta = new Vector2(16, (60 - ((maxhp - hp) * PixperHP)));
        hpString.text = (int)hp + "\n\n" + (int)maxhp;
        hp += 0.005f;
        if (hp < 0) hp = 0;
        if (hp > maxhp) hp = maxhp;
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.gameObject.tag == "Slime")
        {
            Physics2D.IgnoreCollision(collision.collider, c2D);
            Destroy(collision.gameObject);
            animator.SetBool("Slimed", true);
            struggle = 0;
        }
    }


}
