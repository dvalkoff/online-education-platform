import React from "react";
import {Routes, Route} from "react-router-dom";
import SignUp from "./sign/SignUp";
import ConfirmSignUp from "./sign/ConfirmSignUp";
import SignIn from "./sign/SignIn";
import SignUpResult from "./sign/SignUpResult";
import Timetable from "./timetable/Timetable";
import User from "./user/User";

export const AppRouter = () => {
    return (
        <Routes>
            <Route path="sign-up" element={<SignUp/>}/>
            <Route path="sign-up/result" element={<SignUpResult/>}/>
            <Route path="sign-up/confirm" element={<ConfirmSignUp/>}/>
            <Route path="sign-in" element={<SignIn/>}/>
            <Route path="/" element={<Timetable/>}/>
            <Route path="/me" element={<User/>}/>
        </Routes>
    )
}