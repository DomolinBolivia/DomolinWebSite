class DownloadPage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/downloads/download_page.xml");
    }

    // @Override
    async onStart() {
    }

    async onPause(){
    }

    async onDestroy(){
    }

    async onResume(){
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