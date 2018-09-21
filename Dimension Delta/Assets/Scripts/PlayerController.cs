using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {

    public float force = 15;
    public Collider2D ladderC2d;
    public Transform ladderT;
    Rigidbody2D rb;
    Animator animator;
    SpriteRenderer sr;
 
    void Start () {
        animator = GetComponent<Animator>();
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
    }
	
	void FixedUpdate () {
        rb.AddForce(new Vector2(Input.GetAxis("Horizontal") * force, (float).2)); //get input from keyboard/controller
        //Good speed from 15 force, coming to a quick stop is thanks to linear drag on rigid body

        if (rb.velocity.x > 0) //Does the player face right?
        {
            sr.flipX = false; 
        }
        else if(rb.velocity.x < 0) //Or are they facing left?
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
    }

  
}
