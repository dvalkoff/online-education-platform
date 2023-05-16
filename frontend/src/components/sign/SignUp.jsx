import React, {useState, setState} from 'react';
import "./sing-up.css";
import {saveUser} from "../../service/rest/UserRestClient";
import {useNavigate} from "react-router-dom";

function SignUp() {
    const [firstName, setFirstName] = useState(null);
    const [lastName, setLastName] = useState(null);
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const {id, value} = e.target;
        if (id === "firstName") {
            setFirstName(value);
        }
        if (id === "lastName") {
            setLastName(value);
        }
        if (id === "email") {
            setEmail(value);
        }
        if (id === "password") {
            setPassword(value);
        }
    }

    const handleSubmit = () => {
        try {
            let user = {
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password
            };

            saveUser(user).then(() => {
                    console.log("account registered");
                    // TODO: redirect to page with info about confirming an account
                }
            );
        } catch (e) {
            console.error(e);
        }
    }

    return (
        <div className="container">
            <form className="form-signin" onSubmit={() => handleSubmit()}>
                <h2 className="form-signin-heading">Please sign up</h2>
                <p>
                    <label htmlFor="firstName" className="sr-only">First name</label>
                    <input type="text" id="firstName" name="firstName" className="form-control" placeholder="First name"
                           onChange={(e) => handleInputChange(e)}
                           required autoFocus/>
                </p>

                <p>
                    <label htmlFor="lastName" className="sr-only">Last name</label>
                    <input type="text" id="lastName" name="lastName" className="form-control" placeholder="Last name"
                           onChange={(e) => handleInputChange(e)}
                           required/>
                </p>

                <p>
                    <label htmlFor="email" className="sr-only">Email</label>
                    <input type="email" id="email" name="email" className="form-control" placeholder="Email"
                           onChange={(e) => handleInputChange(e)}
                           required/>
                </p>

                <p>
                    <label htmlFor="password" className="sr-only">Password</label>
                    <input type="password" id="password" name="password" className="form-control" placeholder="Password"
                           onChange={(e) => handleInputChange(e)}
                           required/>
                </p>

                <button className="btn btn-lg btn-primary btn-block"
                        type="submit"
                        onClick={() => {
                            handleSubmit();
                            navigate("/sign-up/result")
                        }}
                >
                    Sign up
                </button>

                <a href="/sign-in" className="btn btn-lg btn-success btn-block" role="button" aria-disabled="true">
                    Sign in
                </a>
            </form>


        </div>
    );
}

export default SignUp;