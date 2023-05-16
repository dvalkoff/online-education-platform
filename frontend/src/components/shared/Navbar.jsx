import React from 'react';

function Navbar() {
    return (
        <header className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand" href="#">Online education platform</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup1"
                    aria-controls="navbarNavAltMarkup1" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"/>
            </button>
            <div className="collapse navbar-collapse justify-content-between" id="navbarNavAltMarkup1">
                <div className="navbar-nav mr-auto">
                    <a className="nav-item nav-link" href="/timetable">Timetable</a>
                    <a className="nav-item nav-link" href="/subscriptions">Subscriptions</a>
                    <a className="nav-item nav-link" href="/courses">Find a course</a>
                </div>
                <div className="navbar-nav">
                    <a className="nav-item nav-link" href="/me">My account</a>
                    <a className="nav-item nav-link" href="/logout">Logout</a>
                </div>
            </div>
        </header>
    );
}

export default Navbar;