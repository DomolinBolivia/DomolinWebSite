/* global Thread, Resource, Constants, Toast */
(async () => {
    await Resource.import("pages/about/style.css");
})();

class AboutPage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/about/AboutLayout.xml");
        this.NRO_IMAGENES = 4;
        this.TIEMPO_ROTACION_IMGS = 3000;
        this.timerFotos = new Timer();
        this.fotoActual = 1;
    }

    // @Override
    async onStart() {
//        this.imgFoto = this.findViewById("imgFoto");
//        this.runnable = new Runnable();
//        // let this_ = this;
//        this.runnable.run=()=>{
//            this.siguienteFoto();
//        };
//        this.timerFotos.schedule(this.runnable,this.TIEMPO_ROTACION_IMGS);
    }

    async onPause(){
//        this.timerFotos.stop();
    }

    async onDestroy(){
//        this.timerFotos.stop();
    }

    async onResume(){
//        this.timerFotos.start();
    }

    async siguienteFoto(){
        (this.findViewById(`fraTextoFoto${this.fotoActual}`)).removeCssClass("numeroSeleccionado");
        this.fotoActual++;
        if(this.fotoActual>4)
            this.fotoActual = 1;
        this.imgFoto.setImageResource(`pages/about/photos/ic_photo_${this.fotoActual}.png`);
        (this.findViewById(`fraTextoFoto${this.fotoActual}`)).addCssClass("numeroSeleccionado");
    }
    
    async onClickHome(){
        await this.finish();
        await Resource.import("pages/home/HomePage.js");
        let intent = new Intent(this,"HomePage");
        this.startPage(intent);
    }

    async onClickDescargas(){
        await this.finish();
        await Resource.import("pages/downloads/DownloadPage.js");
        let intent = new Intent(this,"DownloadPage");
        this.startPage(intent);
    }
    
    async onClickAcercaDe(){
        await Toast.makeText(this,"Ya se encuentra en la página",Toast.LENGTH_SHORT);
    }
    
    async onClickLogin(){
        sessionStorage.clear();
        window.location.replace(Config.LOGIN_DOMOLIN);
    }
    
    async onClickWhatSapp(){
        
    }
    
    async onClickTwitter(){
        
    }
    
    async onClickFacebook(){
        
    }
    
    async onClickTicToc(){
        
    }
    
    async onClickInstagram(){
        
    }
    
    async onClickYotube(){
        
    }
};