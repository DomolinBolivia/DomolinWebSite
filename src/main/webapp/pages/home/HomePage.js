/* global Thread, Resource, Constants, Toast */
(async () => {
    await Resource.import("pages/home/style.css");
})();

class HomePage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/home/HomeLayout.xml");
        this.fotoActual = 1;
        this.NRO_IMAGENES = 4;
        this.timerFotos = new Timer();
    }

    // @Override
    async onStart() {
        this.imgFoto = this.findViewById("imgFoto");
        this.runnable = new Runnable();
        // let this_ = this;
        this.runnable.run=()=>{
            this.siguienteFoto();
        };
        this.timerFotos.schedule(this.runnable,3000);
    }

    async onPause(){
        console.log("On pause");
        this.timerFotos.stop();
    }

    async onDestroy(){
        console.log("On destroy");
        this.timerFotos.stop();
    }

    async onResume(){
        console.log("On resume");
        this.timerFotos.start();
    }

    async onClickPruebaGratuita(){
        window.open(Constants.LOGIN_DOMOLIN);
    }

    async siguienteFoto(){
        (this.findViewById(`fraTextoFoto${this.fotoActual}`)).removeCssClass("numeroSeleccionado");
        this.fotoActual++;
        if(this.fotoActual>4)
            this.fotoActual = 1;
        this.imgFoto.setImageResource(`pages/home/photos/ic_photo_${this.fotoActual}.png`);
        (this.findViewById(`fraTextoFoto${this.fotoActual}`)).addCssClass("numeroSeleccionado");
    }

    async anteriorFoto(){
        (this.findViewById(`fraTextoFoto${this.fotoActual}`)).removeCssClass("numeroSeleccionado");
        this.fotoActual--;
        if(this.fotoActual<1)
            this.fotoActual = 4;
        this.imgFoto.setImageResource(`pages/home/photos/ic_photo_${this.fotoActual}.png`);
        (this.findViewById(`fraTextoFoto${this.fotoActual}`)).addCssClass("numeroSeleccionado");
    }

    async onClickSiguienteFoto(){
        this.siguienteFoto();
    }

    async onClickAnteriorFoto(){
        this.anteriorFoto();
    }
    
    
    async onClickHome(){
        await Toast.makeText(this,"Ya se encuentra en la página",Toast.LENGTH_SHORT);
    }
    
    async onClickDescargas(){
        this.finish();
        await Resource.import("pages/downloads/DownloadPage.js");
        let intent = new Intent(this,"DownloadPage");
        this.startPage(intent);
    }
    
    async onClickAcercaDe(){
        this.finish();
        await Resource.import("pages/about/AboutPage.js");
        let intent = new Intent(this,"AboutPage");
        this.startPage(intent);
    }
    
    async onClickLogin(){
        sessionStorage.clear();
        window.location.replace(Constants.LOGIN_DOMOLIN);
    }
};