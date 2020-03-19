import React from "react";
import texts from "../../texts.json";
import "./index.scss";
import { GoogleLogin } from 'react-google-login';
import {connect} from "react-redux";
import {loginUser} from "../../store/actions";

const LoginPage = ({updateUserDetails, promise}) => {
    const responseGoogle = (response) => {
        updateUserDetails(response);
    }

    return (
        <div className="login-container">
            <div className="login-block">
                <img src="images/logo.png" alt="W2W Logo" />
                <div>{texts.pagetitle}</div>
                <GoogleLogin
                    clientId="739919577396-k7710v3cjvccnt0t8vu70c81kk63j7i1.apps.googleusercontent.com"
                    buttonText="Login"
                    onSuccess={responseGoogle}
                    onFailure={(err) => {console.error(err)}}
                    cookiePolicy={'single_host_origin'}
                />
            </div>
        </div>
    );
}

const mapStateToProps = ({ promise }) => ({
    promise
});

const mapDispatchToProps = dispatch => ({
    updateUserDetails: (payload) => dispatch(loginUser(payload))
});

export {LoginPage};

export default connect(mapStateToProps, mapDispatchToProps)(LoginPage);
