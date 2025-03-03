--
-- PostgreSQL database dump
--

-- Dumped from database version 15.8
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bus; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.bus (
    bus_id integer NOT NULL,
    agency character varying(16),
    origin character varying(16),
    destination character varying(16)
);


ALTER TABLE public.bus OWNER TO myuser;

--
-- Name: passenger; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.passenger (
    pass_id integer NOT NULL,
    name character varying(16),
    adress character varying(32),
    tel character varying(16)
);


ALTER TABLE public.passenger OWNER TO myuser;

--
-- Name: bus bus_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.bus
    ADD CONSTRAINT bus_pkey PRIMARY KEY (bus_id);


--
-- Name: passenger passenger_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.passenger
    ADD CONSTRAINT passenger_pkey PRIMARY KEY (pass_id);


--
-- PostgreSQL database dump complete
--

