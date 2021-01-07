/* global FileChooser, Toast */

class DownloadPage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/downloads/Downloadlayout.xml");
    }

    async onClickHome(){
        this.finish();
    }

    async onClickDescargarWindows(){
        Toast.makeText(this,"Por favor espere un momento. Se generara un instalador único.",Toast.LENGTH_LONG);
        await FileChooser.showSaveFile("services/sentinel_download/windows");
    }

    async onClickDescargarLinux(){
        await FileChooser.showSaveFile("pages/downloads/app/smartHubWindows.bat");
    }
};