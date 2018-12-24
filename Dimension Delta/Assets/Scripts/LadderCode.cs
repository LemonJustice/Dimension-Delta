using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LadderCode : MonoBehaviour {

    public Rigidbody2D player;
    public Animator ani;
    public int climb;
    public Animator arms;
    public Animator head;
    GridLayout gridLayout;
    Vector3Int cellPosition;
    Vector3 place;

    void Start()
    {
        gridLayout = transform.parent.GetComponentInParent<GridLayout>();
    }

    private void OnTriggerStay2D(Collider2D other)
    {
        if (other.tag == "Player")
        {
            //Going Up!
            if ((Input.GetKey("up") == true || Input.GetAxis("Vertical") > 0) && !ani.GetBool("Slimed"))
            {
                cellPosition = gridLayout.WorldToCell(new Vector3((int)(other.transform.position.x + .05), other.transform.position.y, other.transform.position.z));
                place = gridLayout.CellToWorld(cellPosition);
                other.transform.SetPositionAndRotation(new Vector2(place.x + (float).5, other.transform.position.y), new Quaternion());
                player.constraints = RigidbodyConstraints2D.FreezeRotation;
                player.AddForce(new Vector2(0, 3 * 12));
                player.velocity.Set(0, player.velocity.y);
                ani.SetInteger("onLadder", 1);
                ani.SetInteger("speed", 0);
                arms.SetBool("onLadder", true);
                head.SetBool("onLadder", true);
            }
            else
            {
                if (Input.GetAxis("Horizontal") > 0)
                {
                    ani.SetInteger("onLadder", 0);
                    arms.SetBool("onLadder", false);
                    head.SetBool("onLadder", false);
                }
            }
        }

    }
    private void OnTriggerExit2D(Collider2D collision)
    {
        player.constraints = RigidbodyConstraints2D.FreezeRotation;
        arms.SetBool("onLadder", false);
        ani.SetInteger("onLadder", 0);
        head.SetBool("onLadder", false);
    }
}
