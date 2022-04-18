<!--  BabylonAES

      * Software: MSCS 630L Semester Project
      * Filename: babylonAES_lab1_v5.php
      * Author: Kerry Lyon
      * Created: March 22, 2022

      * Finalizing lab 1 animation. Added the planes at the bottom
      * that turn visibile after planes slide down.

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
        var endFrame = plainText.length;
        
        for (var i = 0; i < plainText.length; i++) {
          
          // Get letter from the string
          letter = plainText.charAt(i);
          
          var dynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
          var ctx = dynaTex.getContext();
          
          var cipherDynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
          
          // Font sized to fit the plane
          var font_type = "Arial";
          var size = 12;
          var text = "8D";
          ctx.font = size + "px " + font_type;
          var textWidth = ctx.measureText(text).width;
          
          var ratio = textWidth/size;
          
          var font_size = Math.floor(DTWidth / (ratio * 1));
          var font = font_size + "px " + font_type;
          
          var mat = new BABYLON.StandardMaterial("Mat", scene);                    
          mat.diffuseTexture = dynaTex;
          
          var cipherMat = new BABYLON.StandardMaterial("cipherMat", scene);                    
          cipherMat.diffuseTexture = cipherDynaTex;
          
          var plane = BABYLON.Mesh.CreatePlane("Plane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
          plane.material = mat;
          dynaTex.drawText(letter, null, null, font, "blue", "white", true);
          plane.position = new BABYLON.Vector3(i * 1.25, 0, 0);
                              
          // ciphertext plane
          // convert letter to it's numerical value
          if (/\s/.test(letter)) {
              
            num = 26;
            
          } else {
            
            num = letter.toLowerCase().charCodeAt(0) -97;
                    
          }
          
          var cipherPlane = BABYLON.Mesh.CreatePlane("Plane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
          cipherPlane.material = cipherMat;
          cipherDynaTex.drawText(num, null, null, font, "red", "white", true);
          cipherPlane.position = new BABYLON.Vector3(i * 1.25, -2.25, 0);
          cipherPlane.visibility = 0;
          
          // Animation
          var ySlide = new BABYLON.Animation("ySlide", "position.y", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
          
          var visibleTog = new BABYLON.Animation("visibleTog", "visibility", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
          
          var visibleCipher = new BABYLON.Animation("visibleCipher", "visibility", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
                   
          
          // visibility - 0 off, 1 on (default)
          var visFrames = [];
          
          visFrames.push({
            frame: 0,
            value: 1,
          });
          
          visFrames.push({
            frame: i * frameRate / 2,
            value: 1,
          });

          visFrames.push({
            frame: (1 + i) * frameRate / 2,
            value: 1,
          });

          visFrames.push({
            frame: (1 + i) * frameRate / 2,
            value: 0,
          });

          visFrames.push({
            frame: (1 + endFrame) * frameRate / 2,
            value: 0,
          });

          visibleTog.setKeys(visFrames);
          
          // visibility of ciphertext
          var cipherFrames = [];
          
          cipherFrames.push({
            frame: 0,
            value: 0,
          });
          
          cipherFrames.push({
            frame: i * frameRate / 2,
            value: 0,
          });

          cipherFrames.push({
            frame: (1 + i) * frameRate / 2,
            value: 0,
          });

          cipherFrames.push({
            frame: (1 + i) * frameRate / 2,
            value: 1,
          });

          cipherFrames.push({
            frame: (1 + endFrame) * frameRate / 2,
            value: 1,
          });

          visibleCipher.setKeys(cipherFrames);
                    
          // position
          var keyFrames = [];
          
          keyFrames.push({
            frame: 0,
            value: 0,
          });
          
          keyFrames.push({
            frame: i * frameRate / 2,
            value: 0,
          });

          keyFrames.push({
            frame: (1 + i) * frameRate / 2,
            value: -1,
          });

          keyFrames.push({
            frame: (1 + endFrame) * frameRate / 2,
            value: -1,
          });

          ySlide.setKeys(keyFrames);
          
          scene.beginDirectAnimation(plane, [ySlide, visibleTog], 0, (1 + endFrame) * frameRate / 2, false);
          scene.beginDirectAnimation(cipherPlane, [visibleCipher], 0, (1 + endFrame) * frameRate / 2, false);
          
        }
                        
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