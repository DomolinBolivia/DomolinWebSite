/*
(async () => {
    await Resource.import("pages/home/style.css");
})();
 */
/*La clase debe ir con el mismo nonmbre que el archivo*/
class MainPageController extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/mainPageLayout.xml");
    }

    // @Override
    async onStart() {

    }
    async onClickVerVideos() {}
}
;