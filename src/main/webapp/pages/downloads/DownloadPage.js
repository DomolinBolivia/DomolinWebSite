/* global FileChooser, Toast, Constants */

class DownloadPage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/downloads/Downloadlayout.xml");
    }

    async onClickHome(){
        this.finish();
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
        window.location.replace(Constants.LOGIN_DOMOLIN);
    }
};