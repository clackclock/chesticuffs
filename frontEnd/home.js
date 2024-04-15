console.log("working...")
var mapHash = new Map();
mapHash.set('Uber1', 'value1');
mapHash.set('Uber2', 'value2');
mapHash.set('Uber3', 'value3');

//map the button to the card's array and pull

$(document).ready(function(){
   Test();
   //basic web functions
   $("img").hover(function () {
      let v = $(this).attr("src");
      if(v == "../images/chest_Tray2.png" || v == "../images/36_Back.jpg" || v == "../images/chesticuffs_logo.png" || v == "../images/chesticuffs_logoW.png"){
        v = "../images/0_Back.jpg";
      }
      $("#zoom").attr("src", v);
//      console.log(v);
//      $(this).css("background-color", "green");
   }, function () {
       $("#zoom").attr("src", "../images/0_Back.jpg");
   });

   //playing the game
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
   let cdataURL = '../src/Game/CardData/card_image.json';
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
    let cdataURL = '../src/Game/CardData/card_image.json';
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
function placeCard(){
   let cdataURL = '../src/Game/CardData/card_image.json';
   $.ajax({
       url: cdataURL,
       contentType: "application/json",
       data: JSON.stringify("{" + cdataURL + "}"),
       dataType: "json"
   })
   .done(function(data){
        $("img").on("click", "#hand_1", function () {

             let v = $(this).attr("src");
             $(".slot").on("click", function(){
                $(this).attr("src", v);
             })

             $(this).attr("src", data.cardList[randomCardNum()].imageID);
       })

   });
}

function testTst() {
   let ciD = Math.floor(Math.random() * 42);
   console.log(ciD);
   var myCardAPI = "../src/Game/CardData/card_image.json";
   //myCardAPI.html(JSON.stringify(getJSON));
   $.getJSON( myCardAPI, {
     tags: "cardList name",
     tagmode: "any",
     format: "json"
   })
     .done(function( data ) {
//        JSON.stringify(getJSON(myCardAPI));
       $.each( data, function(i, data) {
         //$( "<img>" ).attr( "src", data.imageID ).appendTo( ".hand" );
         console.log(data);
       });

     });
}
function parser(){ //fullPrompt, cb
    let cdataURL = '../src/Game/CardData/card_image.json';
    $.ajax({
        url: cdataURL,
        contentType: "application/json",
        data: JSON.stringify("{" + cdataURL + "}"),
        dataType: "json"
    })
    .done(function(data){
        console.log(data.cardList[0].imageID);
        $("#1").attr("src", data.cardList[0].imageID);
    });
}

