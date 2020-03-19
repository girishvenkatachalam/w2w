import React from "react";
import "./index.scss";
import { GoogleLogin } from 'react-google-login';

const responseGoogle = (response) => {
    console.log(response);
}

const LoginPage = () => <div className="login-container"><GoogleLogin
    clientId="739919577396-k7710v3cjvccnt0t8vu70c81kk63j7i1.apps.googleusercontent.com"
    buttonText="Login"
    onSuccess={responseGoogle}
    onFailure={responseGoogle}
    cookiePolicy={'single_host_origin'}
/></div>;

export default LoginPage;
