import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Layout from "./layout/Layout";
import Footer from "./layout/Footer";
import Main from "../pages/Main";
import Detail from "../pages/Detail";

const Router = () => {
  return (
    <BrowserRouter>
      <Header />
      <Layout>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/detail" element={<Detail />} />
        </Routes>
      </Layout>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
