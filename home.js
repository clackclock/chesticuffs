console.log("working...")
var mapHash = new Map();
mapHash.set('Uber1', 'value1');
mapHash.set('Uber2', 'value2');
mapHash.set('Uber3', 'value3');

//map the button to the card's array and pull

$(document).ready(function(){
//   Test();
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
    $("#hand_1 img").draggable({ opacity: 0.7, helper: "clone", cursor: "move", containment: "document", revert: "invalid" });
    $("#mainPlayer .slot img").droppable({
          accept: "#hand_1 > img",
          drop: function( event, ui ) {
             $(this).attr("src", ui.draggable.attr("src"));
          }
    });
    $("#eRight").droppable({
        accept: "#hand_1 > img",
          drop: function( event, ui ) {
             $(ui.draggable).prependTo(this);
          }
    });
//   $("#hand_1 img").on('click', function(){
//        let re = $(this).attr("src");
//        $("#mainPlayer .slot img").on('click', function(){
//            $(this).attr("src", re);
//        })
//   })
   $("#hand_2 img").draggable({ opacity: 0.7, helper: "clone", cursor: "move", containment: "document", revert: "invalid" });
    $("#otherPlayer .slot img").droppable({
          accept: "#hand_2 > img",
          drop: function( event, ui ) {
             $(this).attr("src", ui.draggable.attr("src"));
          }
    });
    $("#eLeft").droppable({
        accept: "#hand_2 > img",
          drop: function( event, ui ) {
            $(ui.draggable).appendTo(this);
        }
    });
   getHandP1();
   getHandP2();
})

function Test(){
//   document.body.style.color = "red";
   $("p").css("background-color", "yellow");
}
function randomCardNum(){
   let numArr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 34, 35, 36, 37, 38, 39, 40];
   let ciD = numArr[Math.floor(Math.random() * numArr.length)];
   console.log(ciD);
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
       $("#0").attr("src", data.cardList[randomCardNum()].imageID);
       $("#1").attr("src", data.cardList[randomCardNum()].imageID);
       $("#2").attr("src", data.cardList[randomCardNum()].imageID);
       $("#3").attr("src", data.cardList[randomCardNum()].imageID);
       $("#4").attr("src", data.cardList[randomCardNum()].imageID);
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
       $("#5").attr("src", data.cardList[randomCardNum()].imageID);
       $("#6").attr("src", data.cardList[randomCardNum()].imageID);
       $("#7").attr("src", data.cardList[randomCardNum()].imageID);
       $("#8").attr("src", data.cardList[randomCardNum()].imageID);
       $("#9").attr("src", data.cardList[randomCardNum()].imageID);
   });
}
function replaceCard(){
   let cdataURL = 'src/Game/CardData/card_image.json';
   $.ajax({
       url: cdataURL,
       contentType: "application/json",
       data: JSON.stringify("{" + cdataURL + "}"),
       dataType: "json"
   })
   .done(function(data){
        $("#hand_1 img").dblclick( function() {
             //"pick up" after moving the card
             $(this).attr("src", data.cardList[randomCardNum()].imageID);
       })
        $("#hand_2 img").dblclick( function() {
            $(this).attr("src", data.cardList[randomCardNum()].imageID);
        })
   });
}
function removeCard(){
    $(".slot img").on("click", function(){
        let c = $(this).attr("src");
        if(c != "images/chesticuffs_logo.png"){
           $(this).on("dblclick", function(){
               $(this).attr("src", "images/chesticuffs_logo.png");
           }
        )}
    })
}

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


