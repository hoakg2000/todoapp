import React, { useEffect, useState } from "react";
import Header from "./header";
import Footer from "./footer";
import { Redirect, Route, Router, Switch, useHistory } from "react-router-dom";
import { useAuth } from "../context/AuthProvider";
import Login from "../page/login";
import Home from "../page/home";
import Error from "../page/error";

export default function Layout() {


    var { isAuthenticated, checkAuthenticated } = useAuth();
    const [checked, setChecked] = useState<boolean>(false);
    const history = useHistory();

    useEffect(() => {
        checkAuthenticated()
        setChecked(true)
    }, [])

    return (
        <>
            <Header />
            <Switch>
                {!isAuthenticated ?
                    <>
                        <Redirect to={"/login"} />
                        <Route exact path="/login" component={Login} />
                    </> :
                    <>
                    </>
                }
                <Route exact path="/login" component={Login} />
                <Route component={Error} />
            </Switch>
            <Footer />
        </>
    );
}