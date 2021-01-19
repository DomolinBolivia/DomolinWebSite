/* global THREE */

console.log("INICIANDOOOOOOOOOOOO");
window.onload = function () {
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);

    const renderer = new THREE.WebGLRenderer({antialias: true, canvas: document.getElementById('canvasBg')});
    renderer.setSize(window.innerWidth, window.innerHeight);

    camera.position.z = 5;

    // INICIO DE PARTICULAS
    var particles = new THREE.Geometry();
    for (var p = 0; p < 1000; p++) {
        let particle = new THREE.Vector3(Math.random() * 500 - 250, Math.random() * 500 - 250, Math.random() * 500 - 250);
        particles.vertices.push(particle);
    }

    var particleTexture = new THREE.TextureLoader().load( 'assets/bg/particle4B.png' );
    
    var pointsMaterial = new THREE.PointsMaterial({map: particleTexture, transparent: true, size: 5});
    var poinst = new THREE.Points(particles, pointsMaterial);

    scene.add(poinst);
    // FIN DE PARTICULAS

    // RED
    const nroPoints = 50;
    const geometryPointLines = new THREE.Geometry();
    const geometryLines = new THREE.Geometry();
    var materialLines = new THREE.LineBasicMaterial({
        color: 0xb87d1d,
        transparent: true,
        opacity: 0.1,
        linewidth: 1
    });

    let vector1 = null;
    let mod=1;
    for (var p = 0; p < nroPoints; p++) {
        let vector3 = null;
        switch (mod){
            case 1:
                vector3 = new THREE.Vector3(Math.random() * 400 - 250, Math.random() * 400 - 250, Math.random() * 400 - 250);
                vector1 = vector3;
                mod++;
                geometryPointLines.vertices.push(vector3.clone());
                break;
            case 2:
                vector3 = new THREE.Vector3(Math.random() * 400 - 250, Math.random() * 400 - 250, Math.random() * 400 - 250);
                mod++;
                geometryPointLines.vertices.push(vector3.clone());
                break;
            default:
                vector3 = vector1;
                mod=1;
                p--;
                break;
        }
        geometryLines.vertices.push(vector3);
    }
    var texturePointLine = new THREE.TextureLoader().load('assets/bg/light.png');
    var pointsLineMaterial = new THREE.PointsMaterial({map: texturePointLine, transparent: true, size: 8});
    var pointLine = new THREE.Points(geometryPointLines, pointsLineMaterial);
    scene.add(pointLine);

//                const light = new THREE.PointLight( 0xff0000, 1, 100 );
//                light.position.set( 50, 50, 50 );
//                scene.add( light );

//                geometry.vertices.push(new THREE.Vector3(2, -1, 3));
//                geometry.vertices.push(new THREE.Vector3(2, 4, -2));
//                geometry.vertices.push(new THREE.Vector3(1, -3, 2));
//                geometry.vertices.push(new THREE.Vector3(-4, 6, 7));

//                geometry.faces.push(new THREE.Face3( 0, 1, 2, 3));
//                geometry.computeFaceNormals();
//                var mesh = new THREE.Mesh( geometry, new THREE.MeshNormalMaterial({ color: 0xffff00 }) );

    var line = new THREE.Line(geometryLines, materialLines);
    scene.add(line);

    // FIN DE RED

    const animate = function () {
//                    var delta = clock.getDelta();
        requestAnimationFrame(animate);

//                    cube.rotation.x += 0.01;
//                    cube.rotation.y += 0.01;
        poinst.rotation.y += 0.001;
        line.rotation.y += 0.001;
        line.rotation.x += 0.001;
        pointLine.rotation.y += 0.001;
        pointLine.rotation.x += 0.001;
        renderer.render(scene, camera);
    };

    animate();
};