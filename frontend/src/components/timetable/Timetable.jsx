import React, {Fragment} from "react";
import {getNextLessons} from "../../service/rest/TimetableRestClient";
import Navbar from "../shared/Navbar";


export default function Timetable() {
    let lessons = getNextLessons();

    if (lessons.length <= 0) {
        return (
            <div className="rounded bg-primary m-2 p-2">
                <p>
                    You have no lessons! Congratulations!
                </p>
            </div>
        );
    }
    console.log(lessons);
    return (
        <Fragment>
            <Navbar/>
            <p>Your lessons:</p>
            <div className="rounded bg-primary m-2 p-2">
                <tr>
                    {/*{*/}
                    {/*    lessons.map(*/}
                    {/*        lesson => {*/}
                    {/*            return (<p>*/}
                    {/*                <td className="h5">*/}
                    {/*                    Theme: ${lesson.theme}*/}
                    {/*                </td>*/}
                    {/*            </p>);*/}
                    {/*        }*/}
                    {/*    )*/}
                    {/*}*/}
                </tr>
            </div>

        </Fragment>

    );
}