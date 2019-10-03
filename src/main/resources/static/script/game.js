const columnStack = (boardMatrix, colIdx) => {
	for(let rowIdx = 0; rowIdx < 7; rowIdx++) {
		if(rowIdx == 6){
			if(boardMatrix[rowIdx][colIdx] == 0) {
				return rowIdx;
			}
		} else if(boardMatrix[rowIdx+1][colIdx] != 0) {
			return rowIdx;
		}
	}
}

const occupyCheck = (boardString, boardIdx) => {
//	console.log("boardString", boardString);
//	console.log("boardIdx", boardIdx)
//	boardString[0];
	if(boardString.charAt(boardIdx) == "1") {
		return "p_one_token";
	} else if (boardString.charAt(boardIdx) == "2") {
		return "p_two_token";
	} else {
		return "empty";
	}
}

const drawGame = (boardString, boardMatrix = []) => {
    let output = '';
    let boardIdx = 0;

    console.log("boardString in fxn",boardString)
    console.log("current Board: \n");
    
    for (let row = 0; row < 7; row++) {
      output += '<div class="row row'+ row +' empty">';
      boardMatrix.push([]);
      for (col = 0; col < 7; col++) {
        output += "<div class='column column" + col + " " + occupyCheck(boardString, boardIdx) + "' data-column='" + col + "' data-occupy='" + boardString.charAt(boardIdx) + "'></div>";
        boardMatrix[row].push(boardString.charAt(boardIdx));
        boardIdx++;
      }
      console.log(`${boardMatrix[row]}`);
      output += '</div>';
    }
    $("#game-board").append(output);
    return boardMatrix;
}	


$(document).ready(function() {

	let currentBoardString;
	let currentBoardMatrix = [];


	
//	let currentBoardString = $("#game-board").data('current-board');
	
	$.ajax({
		url: "/game/play/board",
        method: "GET"
	}).done((boardRes)=>{
		currentBoardString = boardRes;
		console.log("boardString is",boardRes);
		currentBoardMatrix = drawGame(boardRes, currentBoardMatrix);

		console.log("hello, welcome to the game!");	
	})
	

      // TEST CONTROLS
      
      const board = $("#game-board");
      let playerTurn = $("#game-board").data("current-player");
            
	  $("#game-board").on("click", ".column", function(e) {

      console.log("clicked board")
      console.log("Current player is...", playerTurn)
      
      let column = $(this).data('column');
      console.log("ON CLICK Matrix", currentBoardMatrix)
      let row = columnStack(currentBoardMatrix, column);
      
      let payload = {
          board_data: currentBoardString, //forrealzies
          player: playerTurn,
          i: row,
          j: column
        }
        console.log("KEVINLABS PAYLOAD: " + payload.player);

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
            
            currentBoardString = result;
            
            $("#game-board").attr("data-current-board", result);

            $.ajax({
              url: "/game/play/playerTurn",
              method: "GET"
            })
            .done((playerChange)=>{
            	console.log("AJAX response is..." + playerChange);
            	console.log("before:", $("#game-board").data("current-player"))
              $("#game-board").attr("data-current-player", playerChange);
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
                $("#game-board").append(output);
            })

            

              
              

        	  
        	  
                console.log($("#game-board").data("current-player"))
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
              url: "/game/play/playerTurn",
              method: "GET"
            })
            .done((player)=>{
            	$("#game-board").attr("data-current-player", player);
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
            }).done(()=>{
            	console.log("interval")
            })

        

    	  
      }, 3000);
	// END SCRIPT
})
