using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {

    public float force = 15;
    Rigidbody2D rb;
    Animator animator;
 
    void Start () {
        animator = GetComponent<Animator>();
        rb = GetComponent<Rigidbody2D>();
    }
	
	void FixedUpdate () {
        rb.AddForce(new Vector2(Input.GetAxis("Horizontal") * force, (float).2)); //get input from keyboard/controller
        //Good speed from 15 force, coming to a quick stop is thanks to linear drag on rigid body

        if (rb.velocity.x > 0) //Does the player face right?
        {
            animator.SetInteger("facingRight", 1);
        }
        else if(rb.velocity.x < 0)
        {
            animator.SetInteger("facingRight", 0);
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
