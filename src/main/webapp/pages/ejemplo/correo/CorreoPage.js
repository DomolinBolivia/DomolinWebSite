class CorreoPage extends Dialog {
    constructor(intent) {
        super(intent);
        this.setContentView("pages/ejemplo/correo/correo_layout.xml");
    }

    // Se llama a este metodo cuando termina de cargar la pagina
    async onStart() {

    }

    async onClickSendData(view) {
        // Toast.makeText(this,"Me hiciste click",Toast.LENGTH_SHORT);


        let valido = true;
        if (this.findViewById('textEmail').getText().length === 0) {
            valido = false;
            await this.findViewById('textEmail').showInfoMsg("Debe introducir su correo electronico.");
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
                detail: textDetail}
            let httpRequest = new HttpPost(`services/message/sendMail`);
            httpRequest.setEntity(requestEmail);
            let httpResponse = await httpRequest.execute();
            let responseData = httpResponse.getJson();
            console.log(responseData);
        }





        

       
    }
}
;


