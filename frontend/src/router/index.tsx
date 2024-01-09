import React, { useEffect, useState } from "react";
import { HashRouter, Route, Switch, Redirect, BrowserRouter } from "react-router-dom";
import { useAuth } from "../context/AuthProvider";
import Login from "../page/login";
import Error from "../page/error";
import Home from "../page/home";

export default function App() {
    // global
    var { isAuthenticated, checkAuthenticated } = useAuth();
    const [checked, setChecked] = useState(false);

    useEffect(() => {
        checkAuthenticated()
        setChecked(true)
    }, [])

    return (
        <>
            <BrowserRouter>
                {checked &&
                    <Switch>
                        {isAuthenticated && <Route path="/app" component={Home} />}
                        {!isAuthenticated && <Route path="/login" component={Login} />}
                        <Route
                            exact
                            path="/app"
                            render={() => <Redirect to="/app/dashboard" />}
                        />
                        <Route exact path="/" render={() => <Redirect to="/app/dashboard" />} />
                        <Route component={Error} />
                    </Switch>}
            </BrowserRouter>
        </>
    );

}