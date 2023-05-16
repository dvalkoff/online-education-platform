import React, {useState} from 'react';
import './sing-up.css';
import {authUser} from "../../service/rest/UserRestClient";
import {useNavigate} from "react-router-dom";

export default function SignIn() {
    const [username, setUsername] = useState(null);
    const [password, setPassword] = useState(null);
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const {id, value} = e.target;

        if (id === "username") {
            setUsername(value);
        }
        if (id === "password") {
            setPassword(value);
        }
        console.log(username, password)
    }

    const handleSubmit = () => {
        try {
            let user = {
                username: username,
                password: password
            };

            authUser(user).then((response) => {
                    console.log("account authenticated");
                    // TODO: redirect to main page

                }
            );
        } catch (e) {
            console.error(e);
        }
    }

    return <div className="container">
        <form className="form-signin">
            <h2 className="form-signin-heading">Please sign in</h2>
            <p>
                <label htmlFor="username" className="sr-only">Username</label>
                <input type="text" id="username" name="username" className="form-control" placeholder="Username"
                       onChange={(e) => handleInputChange(e)}
                       required autoFocus/>
            </p>
            <p>
                <label htmlFor="password" className="sr-only">Password</label>
                <input type="password" id="password" name="password" className="form-control" placeholder="Password"
                       onChange={(e) => handleInputChange(e)}
                       required/>
            </p>
            <button className="btn btn-lg btn-primary btn-block"
                    onClick={() => {
                        handleSubmit();
                        navigate("/");
                    }
                    }
                    type="button">
                Sign in
            </button>

            <a href="/sign-up" className="btn btn-lg btn-success btn-block" role="button" aria-disabled="true">Sign
                up</a>

        </form>
    </div>
}