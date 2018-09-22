﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {

    public float force = 15;
    public float jump = 50;
    public Collider2D ladderC2d;
    public Transform ladderT;
    Rigidbody2D rb;
    Animator animator;
    SpriteRenderer sr;
 
    void Start () {
        animator = GetComponent<Animator>();
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
        //rb.constraints = RigidbodyConstraints2D.None;
    }
	
	void FixedUpdate () {
        rb.AddForce(new Vector2(Input.GetAxis("Horizontal") * force, (float).2)); //get input from keyboard/controller
        //Good speed from 23 force, coming to a quick stop is thanks to the 2 linear drag on rigid body and 3 grav

        if (rb.velocity.x > 0 && rb.constraints != RigidbodyConstraints2D.FreezePositionX) //Does the player face right?
        {
            sr.flipX = false; 
        }
        else if(rb.velocity.x < 0 && rb.constraints != RigidbodyConstraints2D.FreezePositionX) //Or are they facing left?
        {
            sr.flipX = true;
        }
        if (Input.GetAxis("Horizontal") != 0) //If there is directional input
        {
            animator.SetInteger("speed", 1);//starts to loop walk animation
        }
        else // No input
        {
            animator.SetInteger("speed" , 0);//Reroutes loop to idle
        }
        if (Input.GetAxis("Jump") > 0 && animator.GetBool("Grounded") == true) //Need to press jump and be on the ground to jump
        {
            animator.SetBool("Jumping", true); //Jump squat
            rb.AddForce(Vector2.up * jump);
            animator.SetBool("Grounded", false);
        }
        if(animator.GetBool("Grounded") == true)
        {
            animator.SetBool("Jumping", false);
        }
    }

  
}
