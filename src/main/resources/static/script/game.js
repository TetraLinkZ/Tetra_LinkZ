$(document).ready(function() {
	let currentBoardString = $("#game-board").data('current-board');
	
	
	let occupyCheck = function(boardIdx){
		let currentBoardString = $("#game-board").data('current-board');

    	if(currentBoardString.charAt(boardIdx) == "1") {
    		return "p_one_token";
    	} else if (currentBoardString.charAt(boardIdx) == "2") {
    		return "p_two_token";
    	} else {
    		return "empty";
    	}
    }
	
	const drawGame = () => {
        let output = '';
        let boardIdx = 0;
        
        
        for (let row = 0; row < 7; row++) {
          output += '<div class="row row'+ row +' empty">';
          for (col = 0; col < 7; col++) {
            output += "<div class='column column" + col + " " + occupyCheck(boardIdx) + "' data-column='" + col + "' data-occupy='" + currentBoardString.charAt(boardIdx) + "'></div>";
            boardIdx++;
          }
          output += '</div>';
        }
        $("#game-board").append(output);
      }

      drawGame();
      console.log("hello, welcome to the game!");

      // TEST CONTROLS
      
      const board = $("#game-board");
            
	  $("#game-board").on("click", ".column", function(e) {
//      $("#game-board").click(function() {
//		e.preventDefault();
		  console.log("clicked")
		  console.log($(this));
		let column = $(this).data('column');
        let payload = {
//          board_data: "0002000001102000222100012220002111000122100012110", //example
          board_data: $("#game-board").attr("data-current-board"), //forrealzies
          player: 2,
          i: 6,
          j: column
        }

        // PROMISE utilization:
        let connectFourWinCheck = $.ajax({
      	  url: "http://kevinalbs.com/connect4/back-end/index.php/hasWon",
  	      method: "GET",
  	      data: payload
        })
        
        let gameAPImove;
        let movePayload = {
          match: 2,
          row: payload.i, 
          column: payload.j,
          user: 2,
          player: payload.player,
        }

        connectFourWinCheck.done((checkResult)=>{
        	console.log("COLUMN: " + column);
        	console.log("game board", currentBoardString);
        	console.log("win check API call result: ", checkResult);
        	
        	movePayload.win = checkResult;
          gameAPImove = $.ajax({
          url: "/game/play/move",
          method: "POST",
          data: movePayload
      	  });
          gameAPImove.done((result)=>{
        	  console.log(movePayload);
        	  $("#game-board").attr("data-current-board", result);
        	  
//        	  $("#game-board").load(window.location.href + " #game-board");
//        	  $("#game-board").load(" #game-board > *");
//        	  $.ajax({
//        		  url: drawGame(),
//        		  success: ()=>{
//        			  console.log('yay')
//        		  }
//        	  })
        	  
              
//        	  let currentBoardStringFxn = result;
////        	  $("#game-board").innerHTML = newBoard;
//        	  let occupyCheckFxn = function(boardIdx){
//        	    	if(currentBoardString.charAt(boardIdx) == "1") {
//        	    		return "p_one_token";
//        	    	} else if (currentBoardString.charAt(boardIdx) == "2") {
//        	    		console.log("player two token inserted")
//        	    		return "p_two_token";
//        	    	} else {
//        	    		return "empty";
//        	    	}
//        	    }
        	  
//        	  var fxn = () =>{
        	  	  currentBoardString = result;
        		  $("#game-board").empty();
        		  let output = '';
                  let boardIdx = 0;
                  
              	let occupyCheck = function(boardIdx, newBoard){
            		let currentBoardString = newBoard;

                	if(currentBoardString.charAt(boardIdx) == "1") {
                		return "p_one_token";
                	} else if (currentBoardString.charAt(boardIdx) == "2") {
                		return "p_two_token";
                	} else {
                		return "empty";
                	}
                }
                  
                  for (let row = 0; row < 7; row++) {
                    output += '<div class="row row'+ row +' empty">';
                    for (col = 0; col < 7; col++) {
                      output += "<div class='column column" + col + " " + occupyCheck(boardIdx, result) + "' data-column='" + col + "' data-occupy='" + currentBoardString.charAt(boardIdx) + "'></div>";
                      boardIdx++;
                    }
                    output += '</div>';
                  }
//                  console.log("output: within fxn: " + output);
                  $("#game-board").append(output);
//                  $("#game-div").load("#game-board");
//                  console.log(output)
//                  return output;
//                  return;
                  
                  
//        	  }
//        	  fxn();
//        	  $("#game-board").load(location.href + " #game-board>*", "")
//    		  $("#game-board").empty();
//        	  $.ajax({
//        		  url: fxn(),
//        	  	  success: ()=>{
//        	  		  console.log('ayy!')
//        	  	  }
//        	  }).done((output)=>{
//        		  
//        		  console.log(output)
////        		  $("#game-board").append(output);
//        	  })
//        	  fxn();
//        	  .done(()=>{
//        		  var newBoard = $("#game-board");
//        		  $.ajax({
//        			  url: fxn(),
//        			  success: ()=>{
//        				  console.log('replaced board');
//        			  }
//        		  })
//        	  })
        	  
        	  
          }).fail((err)=>{
        	  console.log(movePayload);
        	  console.error(err);
        	  console.log(err.responseJSON.error + " : " + err.responseJSON.message);
          })
          
        })
  	
        
        
         // END OF SCOPE
        console.log("hello from POST ajax")
        return false;
      })
      
      setInterval(function(){

        $.ajax({
          url: "/game/play/board",
          method: "GET"
        }).done((result)=>{

          $("#game-board").attr("data-current-board", result);
          console.log("Hello from AJAX interval", result);
          $("#game-board").empty();
            let output = '';
            let boardIdx = 0;
            
          let occupyCheck = function(boardIdx, newBoard){
          let currentBoardString = newBoard;
  
            if(currentBoardString.charAt(boardIdx) == "1") {
              return "p_one_token";
            } else if (currentBoardString.charAt(boardIdx) == "2") {
              return "p_two_token";
            } else {
              return "empty";
            }
          }
            
            for (let row = 0; row < 7; row++) {
              output += '<div class="row row'+ row +' empty">';
              for (col = 0; col < 7; col++) {
                output += "<div class='column column" + col + " " + occupyCheck(boardIdx, result) + "' data-column='" + col + "' data-occupy='" + currentBoardString.charAt(boardIdx) + "'></div>";
                boardIdx++;
              }
              output += '</div>';
            }
  //          console.log("output: within fxn: " + output);

            // console.log($("#game-board").data("current-board"));
            $("#game-board").append(output);
        })

    	  
      }, 3000);
	// END SCRIPT
})
