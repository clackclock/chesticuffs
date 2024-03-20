using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[System.Serializable]

public class Character : MonoBehaviour
{
    //Description
    public int charaID;
    public string charaCardNAME;
    
    //stats
    public int annoy;
    public int paitence;

    public Character(int CharaID, string CharaNAME, int AnnoyX, int PaitI){
        charaID = CharaID;
        charaCardNAME = CharaNAME;
        annoy = AnnoyX;
        paitence = PaitI;

    }

    
}
