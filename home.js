// console.log("working...")
// var mapHash = new Map();
// mapHash.set('Uber1', 'value1');
// mapHash.set('Uber2', 'value2');
// mapHash.set('Uber3', 'value3');
//map the button to the card's array and pull

var siteWidth = 1280;
var scale = screen.width /siteWidth;

var currentplayer = true; //true = p1 false = p2

(()=>{
    const console_log = window.console.log;
    window.console.log = function(...args){
      console_log(...args);
      var textarea = document.getElementById('log');
      if(!textarea) return;
      args.forEach(arg=>textarea.value += `${JSON.stringify(arg)}\n`);
    }
})();

// let hand1 = $("#hand_1").children.length;
// let hand2 = $("#hand_2").children.length;
$(document).ready(function(){
   document.querySelector('meta[name="viewport"]').setAttribute('content', 'width='+siteWidth+', initial-scale='+scale+'');
   //basic web functions
   $("img").hover(function () {
      let v = $(this).attr("src");
      if(v == "images/chest_Tray2.png" || v == "images/36_Back.jpg" || v == "images/chesticuffs_logo.png" || v == "images/chesticuffs_logoW.png" || v == "" || v == "https://upload.wikimedia.org/wikipedia/commons/3/35/Information_icon.svg"){
        v = "images/0_Back.jpg";
      }
      $("#zoom").attr("src", v);
//      console.log(v);
   }, function () {
       $("#zoom").attr("src", "images/0_Back.jpg");
   });

   //playing the game
    getHandP1();
    getHandP2();
    checkHandAmt();

    //for turns < 4 switch between player1_play function player2_play function
    // for(var turns = 3; turns > 0; turns--){
        //play1(); 
        switchPlayer(currentplayer);
        $("#endTurn").click(function(){
            if(currentplayer){ 
                currentplayer = false; 
                // $("#hand_2 img, #otherPlayer .slot img, #eLeft button").draggable("enable");
                // $("#hand_1 img, #mainPlayer .slot img, #eRight button").draggable("disable");
                switchPlayer(currentplayer);
                console.log(" Player 1 has ended their turn");
            } 
            // else{ currentplayer = true; 
            //     // $("#hand_1 img, #mainPlayer .slot img, #eRight button").draggable("enable");
            //     // $("#hand_2 img, #otherPlayer .slot img, #eLeft button").draggable("disable");
            //     switchPlayer(currentplayer);
            //     console.log(" Player 2 has ended their turn");
            // }
            checkHandAmt();
        });
        $("#endTurn2").click(function(){
            if(!currentplayer){ 
                currentplayer = true; 
                // $("#hand_1 img, #mainPlayer .slot img, #eRight button").draggable("enable");
                // $("#hand_2 img, #otherPlayer .slot img, #eLeft button").draggable("disable");
                switchPlayer(currentplayer);
                console.log(" Player 2 has ended their turn");
            }
            checkHandAmt();
        }); 
    //then run calculation where if one player doesn't have any cards left after other player wins

})

function switchPlayer(whichPlayer){
    if(!whichPlayer){play2();} else{play1();}
}
function checkHandAmt(){
    var hand1 = $("#hand_1 img").length;
    var hand2 = $("#hand_2 img").length;
    console.log("Player 1: " + hand1 + " Cards");
    console.log("Player 2: " + hand2 + " Cards");
    console.log("Current: " + currentplayer);
}

function play1(){
    // $("#hand_1 img, #mainPlayer .slot img, #eRight button").draggable("enable");
    // $("#hand_1 img, #mainPlayer .slot img, #eRight button").on("click");
    $("#mainPlayer .slot, #hand_1 img ").css("cursor","crosshair");
    // $("#hand_2 img, #otherPlayer .slot img, #eLeft button").off("click");
    // $("#hand_2 img, #otherPlayer .slot img, #eLeft button").draggable("disable");
    $("#otherPlayer .slot, #hand_2 img").css("cursor","not-allowed");

    $("#hand_1 img").draggable({ opacity: 0.7, helper: "clone", cursor: "move", containment: "document", revert: "invalid" });
    $("#mainPlayer .slot img").droppable({
        accept: "#hand_1 > img",
        drop: function( event, ui ) {
            $(this).attr({src: ui.draggable.attr("src"), alt: ui.draggable.attr("alt")});
            console.log("Player One placed " + $(this).attr("alt"));
        }
          
    });
    $("#eRight").droppable({
        accept: "#hand_1 > img",
        drop: function( event, ui ) {
            $(ui.draggable).prependTo(this);
        }
    });
  $("#hand_1 img").on('click', function(){
       let re = $(this).attr("src");
       $("#mainPlayer .slot img").on('click', function(){
           $(this).attr("src", re);
           console.log("Player One placed " + $(this).attr("alt"));
       })
   });
}
function play2(){
    // $("#hand_2 img, #otherPlayer .slot img, #eLeft button").draggable("enable");
    // $("#hand_2 img, #otherPlayer .slot img, #eLeft button").on("click");
    $("#otherPlayer .slot, #hand_2 img").css("cursor", "crosshair");
    // $("#hand_1 img, #mainPlayer .slot img, #eRight button").off("click");
    // $("#hand_1 img, #mainPlayer .slot img, #eRight button").draggable("disable");
    $("#mainPlayer .slot, #hand_1 img").css('cursor', "not-allowed");

   $("#hand_2 img").draggable({ opacity: 0.7, helper: "clone", cursor: "move", containment: "document", revert: "invalid" });
   $("#otherPlayer .slot img").droppable({
       accept: "#hand_2 > img",
       drop: function( event, ui ) {
           $(this).attr({src: ui.draggable.attr("src"), alt: ui.draggable.attr("alt")});
           console.log("Player Two placed " + $(this).attr("alt"));
       }
   });
   $("#eLeft").droppable({
       accept: "#hand_2 > img",
       drop: function( event, ui ) {
           $(ui.draggable).appendTo(this);
       }
   });
   $("#hand_2 img").on('click', function(){
       let re = $(this).attr("src");
       $("#otherPlayer .slot img").on('click', function(){
           $(this).attr("src", re);
           console.log("Player Two placed " + $(this).attr("alt"));
       })
  })

}

function Test(){
    //   document.body.style.color = "red";
    $("p").css("background-color", "yellow");
}
function randomCardNum(){
   let numArr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 34, 35, 36, 37, 38, 39, 40];
   let ciD = numArr[Math.floor(Math.random() * numArr.length)];
//    console.log(ciD);
   return ciD;
}
function getHandP1(){
   let cdataURL = 'src/Game/CardData/card_image.json';
   $.ajax({
       url: cdataURL,
       contentType: "application/json",
       data: JSON.stringify("{" + cdataURL + "}"),
       dataType: "json"
   })
   .done(function(data){
       //console.log(data.cardList[0].imageID);
       $(function(){
            let bet = randomCardNum();
            $("#0").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
       });
       $(function(){
            let bet = randomCardNum();
            $("#1").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
       });
       $(function(){
            let bet = randomCardNum();
            $("#2").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
       });
       $(function(){
            let bet = randomCardNum();
            $("#3").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
        });
        $(function(){
            let bet = randomCardNum();
            $("#4").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
       });
   });
}
function getHandP2(){
    let cdataURL = 'src/Game/CardData/card_image.json';
   $.ajax({
       url: cdataURL,
       contentType: "application/json",
       data: JSON.stringify("{" + cdataURL + "}"),
       dataType: "json"
   })
   .done(function(data){
       //console.log(data.cardList[0].imageID);
       $(function(){
            let bet = randomCardNum();
            $("#5").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
        });
        $(function(){
            let bet = randomCardNum();
            $("#6").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
        });
        $(function(){
            let bet = randomCardNum();
            $("#7").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
        });
        $(function(){
            let bet = randomCardNum();
            $("#8").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
        });
        $(function(){
            let bet = randomCardNum();
            $("#9").attr({src: data.cardList[bet].imageID, alt: data.cardList[bet].name });
        });
   });
}
function replaceCard(id){
   let cdataURL = 'src/Game/CardData/card_image.json';
   $.ajax({
       url: cdataURL,
       contentType: "application/json",
       data: JSON.stringify("{" + cdataURL + "}"),
       dataType: "json"
   })
   .done(function(data){
        $(id +" img").click( function() {
            //"pick up" after moving the card
            let newt = randomCardNum();
            $(this).attr({src: data.cardList[newt].imageID, alt: data.cardList[newt].name});
       })
   });
}
function removeCard(){
    $(".slot img").on("click", function(){
        let c = $(this).attr("src");
        let a = $(this).attr("alt");
        if(c != "images/chesticuffs_logo.png"){
            console.log(a + " has been removed");
            $(this).attr("src", "images/chesticuffs_logo.png"); 
        }
    })
}
function replaceCardP1(){
    let cdataURL = 'src/Game/CardData/card_image.json';
    $.ajax({
        url: cdataURL,
        contentType: "application/json",
        data: JSON.stringify("{" + cdataURL + "}"),
        dataType: "json"
    })
    .done(function(data){
        $("#hand_1 img").on('click', function() {
            //"pick up" after moving the card
            let newt = randomCardNum();
            $(this).attr({src: data.cardList[newt].imageID, alt: data.cardList[newt].name});
        })
    });
}
// $('#my-dropzone')
//     // crucial for the 'drop' event to fire
//     .on('dragover', false) 

//     .on('drop', function (e) {
//         // do something
//         return false;
//     });

// $("#hand_1 img, #mainPlayer .slot img, #eRight button").prop('disabled', false);
// $("#hand_2 img, #otherPlayer .slot img, #eLeft button").prop("disabled", true);
//
// $("#hand_1 img, #mainPlayer .slot img, #eRight button").prop('disabled', true);
// $("#hand_2 img, #otherPlayer .slot img, #eLeft button").prop("disabled", false);

//2 players: player1 + player2; let currentPlayer; oppositePlayer = player!=currentPlayer
//OR RUN A CHECK ON BOTH PLAYERS INDIVIDUALLY AND RUN THEM FOR BOTH THEIR COINS
//currentPlayer = player1;

//player places a card and can place an item in one turn
//if(currentPlayer.placeItem == true && currentPlayer.placeCard == true){ currentPlayer == oppositePlayer;}
//if(currentPlayer.placeCardInBin == true){ currentPlayer == oppositePlayer(currentPlayer);}

//function moveCard( $item ) {
//  var $slot = $(".slot img");
//  $item.fadeOut(function() {
//      $(".slot img").on("drop", function(){
//        $(this).attr("src", $item.attr("src"));
//      });
//  });
//  $item.fadeOut(function() {
//    $slot.attr("src", $item.attr("src"));
////    $("#hand_1").appendTo('<img id=' + $item.attr("id") + 'src='+data.cardList[randomCardNum()].imageID+'/>');
//  });
//}
    // Card = function(cardArrayID) {
    //    this.cardArrayID = cardArrayID;
    // }
    // $.extend(Card.prototype, {
    //     // object variables
    //     cID: 0, 

    //     init: function(cID) {
    //         // do initialization here
    //         // this.widget_name = widget_name;
    //         this.cID = cID;
            
    //     },
    
    //     doSomething: function() {
    //         // an example object method
    //         // alert('my name is '+this.widget_name);
            
    //         let cdataURL = 'src/Game/CardData/card_image.json';
    //         let d;
    //         $.ajax({
    //             url: cdataURL,
    //             contentType: "application/json",
    //             data: JSON.stringify("{" + cdataURL + "}"),
    //             dataType: "json"
    //         })
    //         .done(function(data){
    //             d = data.cardList[0].cID;
    //             return d;
    //         });
    //         return d;
            
    //     }
    // });
// class Card{
//     //  #cID; #name; #UBER; #ATTACK; #CoreDEFENCE; #CORE; #DEFENCE; #pTYPE; #sTYPE; #imageID;
//     constructor(cardArrayID) {
//         this.cID = (function(){
//             let cdataURL = 'src/Game/CardData/card_image.json';
//             let d;
//             $.ajax({
//                 url: cdataURL,
//                 contentType: "application/json",
//                 data: JSON.stringify("{" + cdataURL + "}"),
//                 dataType: "json"
//             })
//             .done(function(data){
//                 d = data.cardList[cardArrayID].cID;
//                 return d;
//             }); 
//         });
//         // this.name = n;
//         // this.UBER = UBER;
//         // this.ATTACK = ATTACK;
//         // this.CoreDEFENCE = CoreDEFENCE;
//         // this.CORE = CORE;
//         // this.DEFENCE = DEFENCE;
//         // this.pTYPE = pTYPE;
//         // this.sTYPE = sTYPE;
//         // this.imageID = imageID;
        
//         this.cardArrayID = cardArrayID;
//     }

//     getcID(){
//         return this.cID;
        
//     }
// }