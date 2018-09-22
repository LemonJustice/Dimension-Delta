using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GroundDetection : MonoBehaviour {

    public Animator animator;
    public Rigidbody2D player;
	// Use this for initialization
	void Start () {
    
	}

    private void OnCollisionStay2D(Collision2D collision)
    {
        if(collision.rigidbody == player)
        {
            animator.SetBool("Grounded", true);
        }
    }

    private void OnCollisionExit2D(Collision2D collision)
    {
        if(collision.rigidbody == player)
        {
            animator.SetBool("Grounded", false);
        }
    }

}
