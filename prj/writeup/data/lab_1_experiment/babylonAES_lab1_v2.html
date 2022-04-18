<!--  BabylonAES

      * Software: MSCS 630L Semester Project
      * Filename: babylonAES_lab1_v2.php
      * Author: Kerry Lyon
      * Created: March 16, 2022

      * Position animation test for lab 1. Not working properly yet.
      * Updated texture colors / background to be more aesthetically pleasing.

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
        
    <!-- Babylyon.js -->
    <script src="https://cdn.babylonjs.com/babylon.js"></script>
    <script src="https://cdn.babylonjs.com/loaders/babylonjs.loaders.min.js"></script>
        
  </head>
  <body>
    
    <canvas id="renderCanvas"></canvas>
       
    <script>
    
      const canvas = document.getElementById("renderCanvas");
      const engine = new BABYLON.Engine(canvas, true);

      const createScene =  () => {
        
        // initial setup
        const scene = new BABYLON.Scene(engine);
        // Background color
        scene.clearColor = new BABYLON.Color3.FromHexString('#b3e6ff');
        
        // Ambient color
        // scene.ambientColor = new BABYLON.Color3(0.3, 0.3, 0.3);
        
        // Camera
        const camera = new BABYLON.ArcRotateCamera("camera", -Math.PI / 2, Math.PI / 2.5, 5, new BABYLON.Vector3(0, 0, 0), scene);
        camera.attachControl(canvas, true);
        
        // Light
        const light = new BABYLON.HemisphericLight("light", new BABYLON.Vector3(0, 1, 0), scene);
        light.groundColor = new BABYLON.Color3(1,1,1);
        
        /* -------------------------------------------------------- */
        
        // plaintext
        const plainText = "Java AGAIN";
        var letter;
        
        // Create Dynamic Texture
        /* var planeWidth = 1;
        var planeHeight = 1;
        
        var DTWidth = planeWidth * 128;
        var DTHeight = planeHeight * 128;
        
        var myDynamicTexture = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
        var myMaterial = new BABYLON.StandardMaterial("Mat", scene);                    
        myMaterial.diffuseTexture = myDynamicTexture;
        var ctx = myDynamicTexture.getContext();
        
        // var font = "bold 44px monospace";
        
        var font_type = "Arial";
        var size = 12;
        var text = "8D";
        ctx.font = size + "px " + font_type;
        var textWidth = ctx.measureText(text).width;
        
        var ratio = textWidth/size;
        
        var font_size = Math.floor(DTWidth / (ratio * 1)); //size of multiplier (1) can be adjusted, increase for smaller text
        var font = font_size + "px " + font_type; */
        
        // box
        var planeWidth = 1;
        var planeHeight = 1;
        
        var DTWidth = planeWidth * 128;
        var DTHeight = planeHeight * 128;
        
        // var myDynamicTexture = new BABYLON.DynamicTexture("hex", {width:DTWidth, height:DTHeight}, scene);
        // var ctx = myDynamicTexture.getContext();
        
        // var font_type = "Arial";
        // var size = 12;
        // var text = "8D";
        // ctx.font = size + "px " + font_type;
        // var textWidth = ctx.measureText(text).width;
        
        // var ratio = textWidth/size;
        
        // var font_size = Math.floor(DTWidth / (ratio * 1)); //size of multiplier (1) can be adjusted, increase for smaller text
        // var font = font_size + "px " + font_type;        
                
        // var myMaterial = new BABYLON.StandardMaterial("Mat", scene);                    
        // myMaterial.diffuseTexture = myDynamicTexture;
        
        
        // const box = BABYLON.MeshBuilder.CreateBox("box", {width:planeWidth, height:planeHeight}, scene);
        // box.material = myMaterial;        
        // myDynamicTexture.drawText("8D", null, null, font, "blue", "gray", true);
        
        
        /* var mat;
        var dynaTex;
        var cube;
        var num = 1;
        
        letter = "K";
        dynaTex = "myDynamicTexture" + num;
        cube = "box" + num;
        mat = "myMaterial" + num;
        
        var dynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
        var ctx = dynaTex.getContext();
        
        var font_type = "Arial";
        var size = 12;
        var text = "8D";
        ctx.font = size + "px " + font_type;
        var textWidth = ctx.measureText(text).width;
        
        var ratio = textWidth/size;
        
        var font_size = Math.floor(DTWidth / (ratio * 1)); //size of multiplier (1) can be adjusted, increase for smaller text
        var font = font_size + "px " + font_type;
        
        var mat = new BABYLON.StandardMaterial("Mat", scene);                    
        mat.diffuseTexture = dynaTex;
        
        var cube = BABYLON.Mesh.CreateBox("Box", 1.0, scene);
        cube.material = mat;
        dynaTex.drawText(letter, null, null, font, "blue", "white", true); */
        
        // Generate cubes based on the string
        var mat;
        var dynaTex;
        var plane;
        
        for (var i = 0; i < plainText.length; i++) {
          
          // Get letter from the string
          letter = plainText.charAt(i);
          dynaTex = "myDynamicTexture" + i;
          plane = "plane" + i;
          mat = "myMaterial" + i;
          
          // var myDynamicTexture = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
          var dynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
          var ctx = dynaTex.getContext();
          
          var font_type = "Arial";
          var size = 12;
          var text = "8D";
          ctx.font = size + "px " + font_type;
          var textWidth = ctx.measureText(text).width;
          
          var ratio = textWidth/size;
          
          var font_size = Math.floor(DTWidth / (ratio * 1)); //size of multiplier (1) can be adjusted, increase for smaller text
          var font = font_size + "px " + font_type;
          
          var mat = new BABYLON.StandardMaterial("Mat", scene);                    
          mat.diffuseTexture = dynaTex;
          
          var plane = BABYLON.Mesh.CreatePlane("Plane", 1.0, scene);
          plane.material = mat;
          dynaTex.drawText(letter, null, null, font, "blue", "white", true);
          plane.position = new BABYLON.Vector3(i * 1.25, 0, 0);
                    
        }
        
        
        // have a plane with the plaintext
        /* for loop will
          - use Babylon.js animation techniques
          - display a cube for each letter
            - one at a time
          - texture / font sized to fit the cube
          - animate down 1000ms
          - change the texture to the number
          - each successive cube should shift right
            - then go down to line up in the row
          - in the end there will be a row of cubes with the ciphertext numbers
        */
                        
        return scene;
      }

      const scene = createScene();

      engine.runRenderLoop(function () {
        
        scene.render();
        
      });

      window.addEventListener("resize", function () {
        
        engine.resize();
        
      });
        
    </script>
    
  </body>
</html>