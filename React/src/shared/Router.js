import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Layout from "./layout/Layout";
import Footer from "./layout/Footer";
import Main from "../pages/Main";

const Router = () => {
  return (
    <BrowserRouter>
      <Header />
      <Layout>
        <Routes>
          <Route path="/" element={<Main />} />
        </Routes>
        <Footer />
      </Layout>
    </BrowserRouter>
  );
};

export default Router;
