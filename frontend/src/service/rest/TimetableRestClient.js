import axios from 'axios';
import {backendUrl} from "../../config/consts";
import {httpConfigWithCreds} from "../../config/httpConfigs";

// axios.interceptors.request.use(request => {
//     console.log('Starting Request', JSON.stringify(request, null, 2))
//     return request
// })
//
// axios.interceptors.response.use(response => {
//     console.log('Response:', JSON.stringify(response, null, 2))
//     return response
// })

export async function getNextLessons() {
    return await axios.get(
        `${backendUrl}/api/v1/lessons`,
        httpConfigWithCreds)
        .then(response => {
            return JSON.parse(response.data);
        });
}
