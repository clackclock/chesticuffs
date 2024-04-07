console.log("working...")
$(document).ready(function(){
   Test();
   getHandP1();
   testTst();
})

function Test(){
//   document.body.style.color = "red";
   $("p").css("background-color", "yellow");
}

function getHandP1(){
   let ciD = Math.floor(Math.random() * 42);
   if(ciD == 13){
      console.log("run it back");
      ciD = 0;
   }
   console.log(ciD);
      
}
function testTst() {
   let ciD = Math.floor(Math.random() * 42);
   var myCardAPI = "../src/Game/CardData/card_image.json";
   $.getJSON( myCardAPI, {
     tags: "cID, imageID",
     tagmode: "any",
     format: "json"
   })
     .done(function( data ) {
       $.each( data.items, function( i, item ) {
         //$( "<img>" ).attr( "src", item.media.m ).appendTo( "#mainplayer .hand" );
       });
         console.log(data);
     });
}
