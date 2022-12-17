import React from "react";
import { Outlet } from "react-router-dom";
import classes from "./Layout.module.css";
import Header from "./Header";
import Footer from "./Footer";

const Layout = (props) => {
  return (
    <>
      <Header />
      <Outlet className={classes.layout}>{props.children}</Outlet>
      <Footer />
    </>
  );
};

export default Layout;
