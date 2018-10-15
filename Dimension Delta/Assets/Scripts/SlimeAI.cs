using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SlimeAI : MonoBehaviour {

    // Use this for initialization
    private Rigidbody2D rb;
    private SpriteRenderer sr;
    private float coinFlip;
    public int walkingSpeed;
    private bool walking = true;
    private int frameCheck = 60;
	void Start () {
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
     }
	
	// Update is called once per frame
	void Update () {
        if (rb.velocity.x > 0 || walking == false) //Does the slime face right?
        {
            sr.flipX = true;
        }
        else if (rb.velocity.x < 0 ) //Or are they facing left?
        {
            sr.flipX = false;
        }

        if(Time.frameCount % frameCheck == 0)
        {
            coinFlip = Random.value;
            if(coinFlip >= .4 && coinFlip < .8)
            {
                frameCheck = 60;
                walking = true;
                walkingSpeed *= -1;
            }
            else if(coinFlip > .8)
            {
                frameCheck = 120;
                walking = false;
            }
        }

        if (walking)
        {
            rb.AddForce(new Vector2(walkingSpeed, 0));
        }
        
    }
}
