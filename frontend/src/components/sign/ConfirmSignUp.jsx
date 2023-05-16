import React from "react";
import {confirmAnAccount} from "../../service/rest/UserRestClient";

export default function ConfirmSignUp() {
    let urlString = window.location.href;
    let url = new URL(urlString);
    let token = url.searchParams.get("token");

    confirmAnAccount(token).then(() => {
        console.log("Account has been successfully activated");
    });

    return <div className="container">
        <p>Your account has been successfully activated!</p>
        <a href="../" className="btn btn-lg btn-success" role="button" aria-disabled="true">Home page</a>
    </div>
}