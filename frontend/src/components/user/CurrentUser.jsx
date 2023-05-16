import React, {Fragment, useState} from "react";
import Navbar from "../shared/Navbar";
import {getUser, getUserSync} from "../../service/rest/UserRestClient";

export default function CurrentUser() {

    let [user, setUser] = useState(null);

    let username = localStorage.getItem("username");
    getUserSync(username).then(userResp => {
        console.log(`username: ${user}`);
        setUser(userResp);
    });

    return (
        <Fragment>
            <Navbar/>
            <div className="container">
                <p className="display-4"> {user['firstName']} </p>
                <p>Email: ${user.username}</p>
            </div>

            <div className="container">
                <p>Your own courses:</p>
                <div className="row align-items-center rounded bg-primary m-2 p-2">
                    <div className="col">
                        {/*<a className="h5 text-light" name="course_title" th:href="'/courses/' + ${course.getTitle()}"*/}
                        {/*   th:text="${course.getTitle()}"></a>*/}
                        {/*<p className="text-light"*/}
                        {/*   th:text="'Creator: ' + ${course.getOwner().getFirstName()} + ' ' + ${course.getOwner().getLastName()}"></p>*/}
                        {/*<p className="subs text-light" th:text="'Subscribers: ' + ${course.getCountSubscribers()}"></p>*/}
                    </div>
                </div>
            </div>
        </Fragment>
    );
}
