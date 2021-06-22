/* global Resource, FileChooser, Toast */

class CorreoPage extends Dialog {
    constructor(intent) {
        super(intent);
        this.setContentView("pages/about/dialogs/correo/correo_layout.xml");
        this.adjuntos = new Array();
    }

    // Se llama a este metodo cuando termina de cargar la pagina
    async onStart() {

    }
    async onClickClose() {
        this.cancel();
    }

    async onClickSendData(view) {
        // Toast.makeText(this,"Me hiciste click",Toast.LENGTH_SHORT);

        let MAIL_PATTERN = /\S+@\S+\.\S+/;
        let valido = true;

        if (this.findViewById('textEmail').getText().length === 0) {
            valido = false;
            await this.findViewById('textEmail').showInfoMsg("Debe introducir su correo electronico.");
        }
        if (!(MAIL_PATTERN.test(this.findViewById('textEmail').getText()))) {
            this.findViewById('textEmail').showErrorMsg('Correo electrónico no valido');
            valido = false;
        }

        if (this.findViewById('textDetail').getText().length === 0) {
            valido = false;
            await this.findViewById('textDetail').showInfoMsg("Por favor introduzca una breve descripcion de su consulta.");
        }
        if (valido) {
            let textEmail = this.findViewById('textEmail').getText();
            let textPhone = this.findViewById('textPhone').getText();
            let textDetail = this.findViewById('textDetail').getText();

            let requestEmail = {email: textEmail,
                phone: textPhone,
                detail: textDetail,
                archivo: this.adjuntos};

            let httpRequest = new HttpPost(`services/message/sendMail`);
            httpRequest.blockTo(view);
            httpRequest.setEntity(requestEmail);
            let httpResponse = await httpRequest.execute();
            let responseData = httpResponse.getJson();
            console.log(responseData);

            if (responseData.SUCCESS) {
                this.cancel();
                await Resource.import('pages/about/dialogs/successMailSend/SuccessMailDialog.js');
                let dialog = new SuccessMailDialog(this.getContext());
                await dialog.show();
            } else {
                Toast.makeText(this.getContext(), "Nose pudo enviar su consulta", Toast.LENGTH_LONG);
            }
        }
    }

    async onClickSubmitFile() {
        let linAdjuntos = this.findViewById("linAdjuntos");
        let result = await FileChooser.showSelectFile(".pdf, .png, .jpg, .jpeg, .gif, .txt , .zip");
        if (result) {
            let extend = result.fileExt;
            let fileItem = result.dataInBase64;

            let requestEmail = {nombre: result.fileName,
                extension: extend,
                archivoBase64: fileItem};

            this.adjuntos.push(requestEmail);

            let btnAdjunto = new TextView(this);
            await btnAdjunto.setText(result.fileName);
            await btnAdjunto.setBackground("pages/about/dialogs/send_mail/bg_file.9.png");
            btnAdjunto.setSingleLine(true);
            await linAdjuntos.addView(btnAdjunto);

        }
    }
}
;


