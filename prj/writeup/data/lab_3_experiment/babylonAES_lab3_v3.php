<!--  BabylonAES

      * Software: MSCS 630L Semester Project
      * Filename: babylonAES_lab3_v3.php
      * Author: Kerry Lyon
      * Created: March 23, 2022

      * Converting and animating lab 3. Add in animation tests now
      * that the conversion is working.

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
        // const camera = new BABYLON.ArcRotateCamera("camera", -Math.PI / 2, Math.PI / 2.5, 5, new BABYLON.Vector3(20, 0, -35), scene);
        const camera = new BABYLON.ArcRotateCamera("camera", 0, 0, 10, new BABYLON.Vector3(0, 0, 0), scene);
        
        // This positions the camera
        camera.setPosition(new BABYLON.Vector3(-10, 0, -15));
        
        camera.attachControl(canvas, true);
        
        // Light
        const light = new BABYLON.HemisphericLight("light", new BABYLON.Vector3(0, 1, 0), scene);
        light.groundColor = new BABYLON.Color3(1,1,1);
                                      
        // Width / Height
        var planeWidth = 1;
        var planeHeight = 1;
        
        var DTWidth = planeWidth * 128;
        var DTHeight = planeHeight * 128;
                
        const frameRate = 10;
                
        // Generate planes based on the string
        // var endFrame = plainText.length;
        
        // ------------------------ plaintext ------------------------- //
        
        // Sample Input 1
        // var character = "~";
        // var line = "Hola mundo!";
        
        // Sample Input 2
        var character = "0";
        var line = "Een goede naam is beter dan olie.";
        
        // const frameRate = 10;
                
        // Generate planes based on the string
        var endFrame = line.length;
        
        var charDynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
        var ctx = charDynaTex.getContext();
        
        // Font sized to fit the plane
        var font_type = "Arial";
        var size = 12;
        var text = "8D";
        ctx.font = size + "px " + font_type;
        var textWidth = ctx.measureText(text).width;
        
        var ratio = textWidth / size;
        
        var font_size = Math.floor(DTWidth / (ratio * 1));
        var font = font_size + "px " + font_type;
               
        var charMat = new BABYLON.StandardMaterial("Mat", scene);                    
        charMat.diffuseTexture = charDynaTex;
        
        var charPlane = BABYLON.Mesh.CreatePlane("Plane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
        charPlane.material = charMat;
        charDynaTex.drawText(character, null, null, font, "blue", "white", true);
        charPlane.position = new BABYLON.Vector3(-8, 4.25, 0);
        
        for (var i = 0; i < line.length; i++) {
          
          // Get letter from the string
          var letter = line.charAt(i);
          
          var dynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
          var ctx = dynaTex.getContext();
          
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
          
          var plane = BABYLON.Mesh.CreatePlane("Plane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
          plane.material = mat;
          dynaTex.drawText(letter, null, null, font, "blue", "white", true);
          plane.position = new BABYLON.Vector3((i * 1.25) - 8, 3, 0);
          
          animPlainText(letter, i, endFrame)
          
        }
        
        
        
        
        var subChar = character.charAt(0);
        
        var len = Math.ceil(parseFloat(line.length) / 16);
        
        // console.log(subChar);
        // console.log(line.length);
        // console.log(parseFloat(line.length));
        // console.log(parseFloat(line.length) / 16);
        // console.log(len);
        
        const row = 4, col = 4; 
        var matrix = Array(row).fill().map(() => Array(col));
        
        // console.log(matrix);
        
        // var hex = "H";
        // console.log(hex.charCodeAt().toString(16));
        
        var j = 0;
        
        // Since the string is divided into 16 character sections, a string of less than
        // that must be accounted for in case the input string or final loop is smaller.
        for (var i = 0; i < len; i++) {
          
          if (len > 1) {
                      
            if (i < len - 1) {
              
              var subLetter = line.substring(j, j += 16);
              
              matrix = getHexMatP(subChar, subLetter);
              // console.log(matrix);
              hexPlanes(matrix, i);
                                  
                           
              
            } else {
              
              var subLetter = line.substring(j);
              
              matrix = getHexMatP(subChar, subLetter);
              // console.log(matrix);
              hexPlanes(matrix, i);
              
            }
            
            // console.log(matrix);
            
          } else {
            
            matrix = getHexMatP(subChar, line);
            // console.log(matrix);
          
          }
          
          var tac = 0;
          
          for (var x = 0; x < matrix.length; x++) {
            
            for (var y = 0; y < matrix[x].length; y++) {
              
              // console.log(Integer.toHexString(matrix[x][y]).toUpperCase() + " ");
              // console.log(matrix[x][y].toString(16).toUpperCase() + " ");
              
              var matrixChar = matrix[y][x].charCodeAt().toString(16).toUpperCase();
              // console.log(matrixChar);
              
              // console.log(matrix[x][y].charCodeAt().toString(16).toUpperCase());
              
              var matrixDynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
              var ctx = matrixDynaTex.getContext();
              
              // Font sized to fit the plane
              var font_type = "Arial";
              var size = 12;
              var text = "8D";
              ctx.font = size + "px " + font_type;
              var textWidth = ctx.measureText(text).width;
              
              var ratio = textWidth/size;
              
              var font_size = Math.floor(DTWidth / (ratio * 1));
              var font = font_size + "px " + font_type;
              
              var matrixMat = new BABYLON.StandardMaterial("Mat", scene);                    
              matrixMat.diffuseTexture = matrixDynaTex;
              
              var matrixPlane = BABYLON.Mesh.CreatePlane("Plane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
              matrixPlane.material = matrixMat;
              matrixDynaTex.drawText(matrixChar, null, null, font, "blue", "yellow", true);
              matrixPlane.position = new BABYLON.Vector3(((x * 1.25) + (i * 6.25)) - 8, (y * -1.25), -1);
              
              // Animation
              const frameRate = 10;
        
              var visMatrix = new BABYLON.Animation("visMatrix", "visibility", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
              
              var matrixFrames = [];
              
              matrixFrames.push({
                frame: 0,
                value: 0,
              });
              
              matrixFrames.push({
                frame: tac * frameRate / 2,
                value: 0,
              });

              matrixFrames.push({
                frame: (1 + (tac + ((1 + (i * 16))))) * frameRate / 2,
                value: 0,
              });

              matrixFrames.push({
                frame: (1 + (tac + ((1 + (i * 16))))) * frameRate / 2,
                value: 0,
              });
              
              matrixFrames.push({
                frame: (2 + (tac + ((1 + (i * 16))))) * frameRate / 2,
                value: 0,
              });
              
              matrixFrames.push({
                frame: (16 + (tac + ((1 + (i * 16))))) * frameRate / 2,
                value: 0,
              });
              
              matrixFrames.push({
                frame: (16 + (tac + ((1 + (i * 16))))) * frameRate / 2,
                value: 1,
              });

              matrixFrames.push({
                frame: ((len * 16) + 16) * frameRate / 2,
                value: 1,
              });

              visMatrix.setKeys(matrixFrames);
              
              scene.beginDirectAnimation(matrixPlane, [visMatrix], 0, ((len * 16) + 16) * frameRate / 2, false);
                
              tac++;
              
            }
            
            // System.out.println();
            
          }
          
          // System.out.println();
          
        }
        
        
        // Animates the initial plaintext characters
        // dat1 is the character to animate
        // i is current position
        // endFrame length
        function animPlainText(dat1, i, endFrame) {
          
          const frameRate = 10;
          
          var plainDynaTex = new BABYLON.DynamicTexture("PlainTex", {width:DTWidth, height:DTHeight}, scene);
          var ctx = plainDynaTex.getContext();
          
          // Font sized to fit the plane
          var font_type = "Arial";
          var size = 12;
          var text = "8D";
          ctx.font = size + "px " + font_type;
          var textWidth = ctx.measureText(text).width;
          
          var ratio = textWidth/size;
          
          var font_size = Math.floor(DTWidth / (ratio * 1));
          var font = font_size + "px " + font_type;
          
          var plainTextMat = new BABYLON.StandardMaterial("PlainMat", scene);                    
          plainTextMat.diffuseTexture = plainDynaTex;
          
          var plainTextPlane = BABYLON.Mesh.CreatePlane("PTexPlane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
          plainTextPlane.material = plainTextMat;
          plainDynaTex.drawText(dat1, null, null, font, "blue", "yellow", true);
          plainTextPlane.position = new BABYLON.Vector3((i * 1.25) - 8, 3, -1);
          
          var visibleTog = new BABYLON.Animation("visibleTog", "visibility", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
          
          var visFrames = [];
          
          visFrames.push({
            frame: 0,
            value: 0,
          });
          
          visFrames.push({
            frame: i * frameRate / 2,
            value: 0,
          });

          visFrames.push({
            frame: (1 + i) * frameRate / 2,
            value: 0,
          });

          visFrames.push({
            frame: (1 + i) * frameRate / 2,
            value: 1,
          });
          
          visFrames.push({
            frame: (2 + i) * frameRate / 2,
            value: 1,
          });
          
          visFrames.push({
            frame: (2 + i) * frameRate / 2,
            value: 0,
          });
          
          visFrames.push({
            frame: (3 + i) * frameRate / 2,
            value: 0,
          });

          visFrames.push({
            frame: (1 + endFrame) * frameRate / 2,
            value: 0,
          });

          visibleTog.setKeys(visFrames);
          
          scene.beginDirectAnimation(plainTextPlane, [visibleTog], 0, (1 + endFrame) * frameRate / 2, false);
          
        }
        
        // dat1 is x position
        // dat2 is y poistion
        // dat3 is the character
        // dat4 is value of i
        function hexPlanes(matrix, dat4) {
          
          var tic = 0;
          
          for (var x = 0; x < 4; x++) {
          
            for (var y = 0; y < 4; y++) {
              
              var hexChar = matrix[y][x];
                      
              var hexDynaTex = new BABYLON.DynamicTexture("Hex", {width:DTWidth, height:DTHeight}, scene);
              var ctx = hexDynaTex.getContext();
              
              // Font sized to fit the plane
              var font_type = "Arial";
              var size = 12;
              var text = "8D";
              ctx.font = size + "px " + font_type;
              var textWidth = ctx.measureText(text).width;
              
              var ratio = textWidth/size;
              
              var font_size = Math.floor(DTWidth / (ratio * 1));
              var font = font_size + "px " + font_type;
              
              var hexMat = new BABYLON.StandardMaterial("hexMat", scene);                    
              hexMat.diffuseTexture = hexDynaTex;
              
              var hexPlane = BABYLON.Mesh.CreatePlane("hexPlane", 1.0, scene, true, BABYLON.Mesh.DOUBLESIDE);
              hexPlane.material = hexMat;
              hexDynaTex.drawText(hexChar, null, null, font, "blue", "white", true);
              hexPlane.position = new BABYLON.Vector3(((x * 1.25) + (dat4 * 6.25)) - 8, y * -1.25, 0);
              
              // Animation
              const frameRate = 10;
        
              var hexMatrix = new BABYLON.Animation("hexMatrix", "visibility", frameRate, BABYLON.Animation.ANIMATIONTYPE_FLOAT, BABYLON.Animation.ANIMATIONLOOPMODE_CONSTANT);
              
              var hexFrames = [];
              
              hexFrames.push({
                frame: 0,
                value: 0,
              });
              
              hexFrames.push({
                frame: tic * frameRate / 2,
                value: 0,
              });

              hexFrames.push({
                frame: (1 + (tic + ((1 + (dat4 * 16))))) * frameRate / 2,
                value: 0,
              });

              hexFrames.push({
                frame: (1 + (tic + ((1 + (dat4 * 16))))) * frameRate / 2,
                value: 1,
              });
              
              hexFrames.push({
                frame: (2 + (tic + ((1 + (dat4 * 16))))) * frameRate / 2,
                value: 1,
              });
              
              hexFrames.push({
                frame: (2 + (tic + ((1 + (dat4 * 16))))) * frameRate / 2,
                value: 1,
              });

              hexFrames.push({
                frame: ((len * 16) + 1) * frameRate / 2,
                value: 1,
              });

              hexMatrix.setKeys(hexFrames);
              
              scene.beginDirectAnimation(hexPlane, [hexMatrix], 0, ((len * 16) + 1) * frameRate / 2, false);
            
              tic++;
              // console.log(tic);
              
            }
            
          }
                    
        }
        
        
        
        /**
         * getHexMatP
         *
         * This function receives the substitution character and a plaintext
         * string with a length less than or equal to 16. The resulting
         * matrix is repeatedly printed each time it loops.
         *
         * Parameters:
         *  char s: substitution character
         *  String p: plaintext
         *
         * Return value: at least 4 columns of the matrix
         */
        function getHexMatP(s, p) {
          
          const row = 4, col = 4; 
          var result = Array(row).fill().map(() => Array(col));
                          
          for (var i = 0; i < 16; i++) {
                     
            if (i < p.length) {
              
              result[i % 4][Math.floor(i / 4)] = p.charAt(i);
              // console.log(result[i % 4][Math.floor(i / 4)]);
              
                        
            } else {
              
              result[i % 4][Math.floor(i / 4)] = s;
              // console.log(result[i % 4][Math.floor(i / 4)]);
              
              
            }
                  
          }
              
          return result;
          
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