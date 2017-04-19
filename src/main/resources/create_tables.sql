CREATE TABLE public.account
(
  userid integer NOT NULL,
  name character varying(100) NOT NULL,
  CONSTRAINT account_pkey PRIMARY KEY (userid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.account
  OWNER TO postgres;

CREATE TABLE public.project
(
  projectid integer NOT NULL,
  name character varying(100) NOT NULL,
  userid integer NOT NULL,
  CONSTRAINT project_pkey PRIMARY KEY (projectid),
  CONSTRAINT project_user_fkey FOREIGN KEY (userid)
      REFERENCES public.account (userid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.project
  OWNER TO postgres;

CREATE INDEX fki_project_user_fkey
  ON public.project
  USING btree
  (userid);
