import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "../pages/Main";
import Layout from "./layout/Layout";
// import Header from "../components/layout/Header";
import Footer from "./layout/Footer";

const Router = () => {
  return (
    <BrowserRouter>
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
