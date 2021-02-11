/* global FileChooser, Toast, Constants, Resource */

class DownloadPage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/downloads/Downloadlayout.xml");
    }

    async onClickDescargarWindows(){
        var this_= this;
        (async () => {
            await Toast.makeText(this_,"Por favor espere un momento. Se generara un instalador único.",Toast.LENGTH_LONG);
        });
        await FileChooser.showSaveFile("download/windows/centinela.zip");
    }

    async onClickDescargarLinux(){
        var this_= this;
        (async () => {
            await Toast.makeText(this_,"Por favor espere un momento. Se generara un instalador único.",Toast.LENGTH_LONG);
        });
        await FileChooser.showSaveFile("download/linux/centinela.zip");
    }
    
    async onClickLogin(){
        sessionStorage.clear();
        window.location.replace(Config.LOGIN_DOMOLIN);
    }

    async onClickHome(){
        await this.finish();
        await Resource.import("pages/home/HomePage.js");
        let intent = new Intent(this,"HomePage");
        this.startPage(intent);
    }
    
    async onClickDescargas(){
        await Toast.makeText(this,"Ya se encuentra en la página",Toast.LENGTH_SHORT);
    }
    
    async onClickAcercaDe(){
        await this.finish();
        await Resource.import("pages/about/AboutPage.js");
        let intent = new Intent(this,"AboutPage");
        this.startPage(intent);
    }
};