import React from "react";
import classes from "./Elements.module.css";

const Button = ({ className, onClick, children }) => {
  return (
    <button className={`${classes.button} ${className}`} onClick={onClick}>
      {children}
    </button>
  );
};

export default Button;
