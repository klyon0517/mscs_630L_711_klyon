<!--  BabylonAES

      * Software: MSCS 630L Semester Project
      * Filename: babylonAES_lab1_v4.php
      * Author: Kerry Lyon
      * Created: March 22, 2022

      * This script continues building on the lab 1 experiments.
      * Testing visibility and material animation.
      * No luck getting materias to animate yet, but
      * visibility works great.

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
        const camera = new BABYLON.ArcRotateCamera("camera", -Math.PI / 2, Math.PI / 2.5, 5, new BABYLON.Vector3(5, 0, -5), scene);
        camera.attachControl(canvas, true);
        
        // Light
        const light = new BABYLON.HemisphericLight("light", new BABYLON.Vector3(0, 1, 0), scene);
        light.groundColor = new BABYLON.Color3(1,1,1);
                        
        // plaintext
        const plainText = "Java AGAIN";
        var letter;
        var num;
                       
        // Width / Height
        var planeWidth = 1;
        var planeHeight = 1;
        
        var DTWidth = planeWidth * 128;
        var DTHeight = planeHeight * 128;
                
        const frameRate = 10;
                
        // Generate planes based on the string
        var mat;
        var dynaTex;
        var plane;
        var yslide;
        var endFrame = plainText.length;
        
        for (var i = 0; i < plainText.length; i++) {
          
          // Get letter from the string
          letter = plainText.charAt(i);
          dynaTex = "myDynamicTexture" + i;
          plane = "plane" + i;
          mat = "myMaterial" + i;
          yslide = "yslide" + i;
          
          // var myDynamicTexture = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
          var dynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
          var ctx = dynaTex.getContext();
          
          // Font sized to fit the plane
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
          
          var mat1 = new BABYLON.StandardMaterial("Mat1", scene);                    
          mat1.diffuseColor = new BABYLON.Color3(0.5, 0.6, 0.87);
          
          var mat2 = new BABYLON.StandardMaterial("Mat2", scene);                    
          mat2.diffuseColor = new BABYLON.Color3(0.23, 0.98, 0.53);
          
          var plane = BABYLON.Mesh.CreatePlane("Plane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
          plane.material = mat;
          // plane.material = mat1;
          dynaTex.drawText(letter, null, null, font, "blue", "white", true);
          plane.position = new BABYLON.Vector3(i * 1.25, 0, 0);
          
          // Animation
          ySlide = new BABYLON.Animation("ySlide" + i, "position.y", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
          
          visibleTog = new BABYLON.Animation("visibleTog" + i, "visibility", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
          
          matChange = new BABYLON.Animation("matChange" + i, "material", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
          
          // ySlide = new BABYLON.Animation("ySlide" + i, "diffuseTexture", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
          
          
          // TEST animation:
          // visibility - 0 off, 1 on (default)
          var visFrames = [];
          
          visFrames.push({
            frame: 0,
            value: 1,
          });
          
          visFrames.push({
            frame: i * frameRate,
            value: 1,
          });

          visFrames.push({
            frame: (2 + i) * frameRate,
            value: 1,
          });

          visFrames.push({
            frame: (2 + i) * frameRate,
            value: 0,
          });

          visFrames.push({
            frame: (2 + endFrame) * frameRate,
            value: 0,
          });

          visibleTog.setKeys(visFrames);
          
          // material - diffuseColor
          // can't materials to animate at the moment         
          var matFrames = [];
          
          matFrames.push({
            frame: 0,
            value: mat1,
          });
          
          matFrames.push({
            frame: i * frameRate,
            value: mat1,
          });

          matFrames.push({
            frame: (2 + i) * frameRate,
            value: mat2,
          });

          matFrames.push({
            frame: (2 + endFrame) * frameRate,
            value: mat2,
          });

          matChange.setKeys(matFrames);
          
          // position
          var keyFrames = [];
          
          keyFrames.push({
            frame: 0,
            value: 0,
          });
          
          keyFrames.push({
            frame: i * frameRate,
            value: 0,
          });

          keyFrames.push({
            frame: (2 + i) * frameRate,
            value: -3,
          });

          keyFrames.push({
            frame: (2 + endFrame) * frameRate,
            value: -3,
          });

          ySlide.setKeys(keyFrames);
          
          plane.animations.push(ySlide);
          scene.beginDirectAnimation(plane, [ySlide, visibleTog], 0, (2 + endFrame) * frameRate, false);
          
          // ciphertext
          /* if (/\s/.test(letter)) {
              
            num = 26;
            
          } else {
            
            num = letter.toLowerCase().charCodeAt(0) -97;
                    
          }
          
          dynaTex.drawText(num, null, null, font, "blue", "white", true); */

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