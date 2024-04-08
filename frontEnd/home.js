console.log("working...")
$(document).ready(function(){
   Test();
   //getHandP1();
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
   console.log(ciD);
   var myCardAPI = "../src/Game/CardData/card_image.json";
   let jbb = $.getJSON( myCardAPI, {
     tags: "cardList, name, cID, imageID",
     tagmode: "any",
     format: "json"
   })
//     .done(function( data ) {
//       $.each( data, function(i, data ) {
//         //$( "<img>" ).attr( "src", data.imageID ).appendTo( ".hand" );
//       });
//         console.log(data.name);
//     });

   .done(function(jbb){
        $.each(jbb, function (index, jbb) {
            console.log(jbb)
        });
   })
}
