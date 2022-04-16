<!--  BabylonAES

      * Software: MSCS 630L Semester Project
      * Filename: babylonAES_lab1_v1.php
      * Author: Kerry Lyon
      * Created: March 14, 2022

      * Initial conversion of lab 1 to JavaScript.
      * First tests on dynamic textures.

-->



<!DOCTYPE html>
<html  xmlns="http://www.w3.org/1999/xhtml">
	<head>
  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="BabylonAES" />
    <meta name="keywords" content="aes, encryption, animation, babylonjs, marist, college, cryptography, algorithms" />
    <meta name="author" content="Kerry Lyon" />
		
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<title>BabylonAES</title>
    
    <style>
    
      html, body {
        overflow: hidden;
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
      }

      #renderCanvas {
        width: 100%;
        height: 100%;
        touch-action: none;
      }
      
    </style>
                
		<!-- Google Font -->
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		
		<!-- Font Awesome -->
		<script src="https://kit.fontawesome.com/c68564576b.js" crossorigin="anonymous"></script>
    
    <!-- Babylyon.js -->
    <script src="https://cdn.babylonjs.com/babylon.js"></script>
    <script src="https://cdn.babylonjs.com/loaders/babylonjs.loaders.min.js"></script>
        
  </head>
  <body>
    <div style="position: relative;  height: 100%; width: 100%;">
      
      <!-- <button id="" class="" style="position: absolute; top: 75px; left: 75px; height: 25px; width: 100px; z-index: 5;" type="button" onclick="makeCube()">CUBOID</button> -->
      <canvas id="renderCanvas"></canvas>
      
    </div>
    
    <script>
    
      const canvas = document.getElementById("renderCanvas"); // Get the canvas element
      const engine = new BABYLON.Engine(canvas, true); // Generate the BABYLON 3D engine

      // Add your code here matching the playground format
      const createScene =  () => {
        
        // initial setup
        const scene = new BABYLON.Scene(engine);

        const camera = new BABYLON.ArcRotateCamera("camera", -Math.PI / 2, Math.PI / 2.5, 5, new BABYLON.Vector3(0, 0, 0), scene);
        camera.attachControl(canvas, true);

        const light = new BABYLON.HemisphericLight("light", new BABYLON.Vector3(0, 1, 0), scene);
                
        // box
        var planeWidth = 1;
        var planeHeight = 1;
        const box = BABYLON.MeshBuilder.CreateBox("box", {width:planeWidth, height:planeHeight}, scene);
        
        var DTWidth = planeWidth * 128;
        var DTHeight = planeHeight * 128;
        
        var myDynamicTexture = new BABYLON.DynamicTexture("hex", {width:DTWidth, height:DTHeight}, scene);
        var ctx = myDynamicTexture.getContext();
        
        var font_type = "Arial";
        var size = 12;
        var text = "8D";
        ctx.font = size + "px " + font_type;
        var textWidth = ctx.measureText(text).width;
        
        var ratio = textWidth/size;
        
        var font_size = Math.floor(DTWidth / (ratio * 1)); //size of multiplier (1) can be adjusted, increase for smaller text
        var font = font_size + "px " + font_type;        
        
        myDynamicTexture.drawText("8D", null, null, font, "blue", "gray", true);
        
        var myMaterial = new BABYLON.StandardMaterial("Mat", scene);                    
        myMaterial.diffuseTexture = myDynamicTexture;
        box.material = myMaterial;
        
        // plaintext
        const plainText = "Java AGAIN";
        
        const plane1 = BABYLON.MeshBuilder.CreatePlane("plane1", {height:1, width: 2}, scene);
        plane1.position = new BABYLON.Vector3(0, 1.25, 0);
        
        var myPlainTx = new BABYLON.DynamicTexture("plnTx", {width:512, height:256}, scene);
        
        var plane1Mat = new BABYLON.StandardMaterial("Mat1", scene);    				
        plane1Mat.diffuseTexture = myPlainTx;
        plane1.material = plane1Mat;
        
        var font2 = "bold 44px monospace";
        myPlainTx.drawText(plainText, 50, 125, font2, "blue", "gray", true);
        
        // conversion characters to numbers
        const numArray = [];
        var letter;
        let ciphertext;
        
        function sleep(ms) {
          
          return new Promise(resolve => setTimeout(resolve, ms));
            
        }
        
        async function demo() {
                  
          const plane2 = BABYLON.MeshBuilder.CreatePlane("plane2", {height:1, width: 2}, scene);
          plane2.position = new BABYLON.Vector3(0, -1.25, 0);
          
          var myCipherTx = new BABYLON.DynamicTexture("cipTx", {width:768, height:256}, scene);
          
          var plane2Mat = new BABYLON.StandardMaterial("Mat2", scene);    				
          plane2Mat.diffuseTexture = myCipherTx;
          plane2.material = plane2Mat;
          
          var font2 = "bold 44px monospace";
          myCipherTx.drawText("", 50, 125, font, "blue", "gray", true);
          
          for (let i = 0; i < plainText.length; i++) {
                
            letter = plainText.charAt(i);
            
            await sleep(1000);
            // change texture to match letter
            myDynamicTexture.drawText(letter, null, null, font, "blue", "gray", true);
            
            if (/\s/.test(letter)) {
              
              num = 26;
              
            } else {
              
              num = letter.toLowerCase().charCodeAt(0) -97;
                      
            }
            
            numArray[i] = num;
            
            ciphertext = numArray.join("");
            
            await sleep(1000);
            myCipherTx.drawText(ciphertext, 50, 125, font2, "blue", "gray", true);
            
            // wait a second between loops
            // await sleep(1000);
            
          }
                    
          
          
          // myDynamicTexture.drawText("8D", null, null, font, "blue", "gray", true);
          
        }
        
        
        // run the loops
        demo();       
                
        // let cyphertext = numArray.toString();
        // let cyphertext = numArray.join("");
        
        // cyphertext
        // const cyphertext = "nums";
        
        // const plane2 = BABYLON.MeshBuilder.CreatePlane("plane2", {height:1, width: 2}, scene);
        // plane2.position = new BABYLON.Vector3(0, -1.25, 0);
        
        // var myCypherTx = new BABYLON.DynamicTexture("cypTx", {width:768, height:256}, scene);
        
        // var plane2Mat = new BABYLON.StandardMaterial("Mat2", scene);    				
        // plane2Mat.diffuseTexture = myCypherTx;
        // plane2.material = plane2Mat;
        
        // var font = "bold 44px monospace";
        // myCipherTx.drawText("8D", 50, 125, font, "blue", "gray", true);
                
        return scene;
      }

      const scene = createScene(); //Call the createScene function

      // Register a render loop to repeatedly render the scene
      engine.runRenderLoop(function () {
        
        scene.render();
        
      });

      // Watch for browser/canvas resize events
      window.addEventListener("resize", function () {
        
        engine.resize();
        
      });
        
    </script>
    
    <!-- Custom Script -->
		<!-- <script type="text/javascript" src="scripts/main.js"></script> -->
    
  </body>
</html>