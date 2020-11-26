/* global FileChooser */

class DownloadPage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/downloads/Downloadlayout.xml");
    }

    async onClickHome(){
        this.finish();
    }

    async onClickDescargarWindows(){
        await FileChooser.showSaveFile("smartHubWindows.bat","pages/downloads/app/smartHubWindows.bat");
    }

    async onClickDescargarLinux(){
        await FileChooser.showSaveFile("smartHubWindows.bat","pages/downloads/app/smartHubWindows.bat");
    }
};