using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LadderCode : MonoBehaviour {

    public Rigidbody2D player;
    public int climb;
    GridLayout gridLayout;
    Vector3Int cellPosition;
    Vector3 place;

    void Start()
    {
        gridLayout = transform.parent.GetComponentInParent<GridLayout>();
    }

    private void OnTriggerStay2D(Collider2D other)
    {
        //Going Up!
        if (Input.GetKey("up") == true || Input.GetAxis("Vertical") > 0)
        {
            cellPosition = gridLayout.WorldToCell(new Vector3((int)(other.transform.position.x + .05), other.transform.position.y, other.transform.position.z));
            place = gridLayout.CellToWorld(cellPosition);
            other.transform.SetPositionAndRotation(new Vector2(place.x + (float).5, other.transform.position.y), new Quaternion());
            player.constraints = RigidbodyConstraints2D.FreezeRotation;
            player.AddForce(new Vector2(0, 3 * climb));
            player.velocity.Set(0, player.velocity.y);
        }
        else
        {
           
        }

    }
    private void OnTriggerExit2D(Collider2D collision)
    {
        player.constraints = RigidbodyConstraints2D.FreezeRotation;
    }
}
