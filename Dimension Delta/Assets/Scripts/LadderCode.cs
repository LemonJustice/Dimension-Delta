using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LadderCode : MonoBehaviour {

    private Transform lT;
    public Rigidbody2D player;
    GridLayout gridLayout;
    Vector3Int cellPosition;
    Vector3 place;

    void Start()
    {
        lT = GetComponent<Transform>();
        gridLayout = transform.parent.GetComponentInParent<GridLayout>();
    }

    private void OnTriggerStay2D(Collider2D other)
    {
        if(Input.GetKeyDown("up") == true)
        {
            //cellPosition = gridLayout.WorldToCell(new Vector3((int)(other.transform.position.x + .05), other.transform.position.y, other.transform.position.z));
            //place = gridLayout.CellToWorld(cellPosition);
            //other.transform.SetPositionAndRotation(new Vector2(place.x + (float).5, other.transform.position.y), new Quaternion());
            //player.constraints = RigidbodyConstraints2D.FreezePositionX;
        }
        else
        {
            //player.constraints = RigidbodyConstraints2D.None;
        }
    }
    private void OnTriggerExit2D(Collider2D collision)
    {
        //player.constraints = RigidbodyConstraints2D.None;
    }
}
