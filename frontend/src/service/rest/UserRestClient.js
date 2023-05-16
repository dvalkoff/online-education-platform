import axios from 'axios';
import {backendUrl} from "../../config/consts";
import {httpConfig, httpConfigWithCreds} from "../../config/httpConfigs";


export async function saveUser(userSignUpDto) {
    try {
        return await axios.post(
            `${backendUrl}/api/v1/sign-up`,
            JSON.stringify(userSignUpDto),
            httpConfig
        )
    } catch (e) {
        console.log(e);
    }
}

export async function confirmAnAccount(token) {
    try {
        return await axios.put(
            `${backendUrl}/api/v1/sign-up/confirm?token=${token}`,
            null,
            httpConfig
        )
    } catch (e) {
        console.log(e);
    }
}

export function authUser(authDto) {
    try {
        // TODO: rewrite using axios
        return fetch(`${backendUrl}/api/v1/sign-in`, {
            credentials: "include",
            method: 'POST',
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded',

            },
            body: new URLSearchParams({
                'username': authDto.username,
                'password': authDto.password
            })
        }).then(() => {
                localStorage.setItem("username", authDto.username)
        });
    } catch (e) {
        console.log(e);
    }
}

export async function getUser(username) {
    return await axios.get(
        {
            credentials: "include",
            method: 'GET',
            headers:{
                'Content-Type': 'application/json'
            },
        }
        `${backendUrl}/api/v1/users/${username}`)
        .then(response => {
            return response.data;
        });
}

export async function getUserSync(username) {
    return await fetch(
        `${backendUrl}/api/v1/users/${username}`,
        {
            credentials: "include",
            method: 'GET',
            headers:{
                'Content-Type': 'application/json'
            },
        }).then(response => {
            return response.json();
        });
}