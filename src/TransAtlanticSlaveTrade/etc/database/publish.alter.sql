ALTER TABLE submissions_edited_voyages DROP CONSTRAINT fk_sev_to_voyages;
ALTER TABLE submissions_edited_voyages   ADD CONSTRAINT fk_sev_to_voyages FOREIGN KEY (voyage_iid) REFERENCES voyages (iid) ON DELETE CASCADE;      

ALTER TABLE submissions_edit DROP CONSTRAINT fk_editor_old_voyage_submissions_edit_to_submissions_edited_voy;
ALTER TABLE submissions_edit ADD CONSTRAINT fk_editor_old_voyage_submissions_edit_to_submissions_edited_voy FOREIGN KEY (old_edited_voyage_id) REFERENCES submissions_edited_voyages (id)  ON DELETE CASCADE;

ALTER TABLE submission_editors DROP CONSTRAINT fk_submission_users_to_edited_voyages;
ALTER TABLE submission_editors ADD CONSTRAINT fk_submission_users_to_edited_voyages FOREIGN KEY (edited_voyage_id) REFERENCES submissions_edited_voyages (id)  ON DELETE CASCADE;

ALTER TABLE submissions_edit DROP CONSTRAINT fk_editor_edited_voyage_submissions_edit_to_submissions_edited_;
ALTER TABLE submissions_edit ADD CONSTRAINT fk_editor_edited_voyage_submissions_edit_to_submissions_edited_ FOREIGN KEY (editor_edited_voyage_id) REFERENCES submissions_edited_voyages (id)  ON DELETE CASCADE;

ALTER TABLE submissions_edit DROP CONSTRAINT fk_new_edited_voyage_submissions_edit_to_submissions_edited_voy;
ALTER TABLE submissions_edit ADD CONSTRAINT fk_new_edited_voyage_submissions_edit_to_submissions_edited_voy FOREIGN KEY (new_edited_voyage_id) REFERENCES submissions_edited_voyages (id)  ON DELETE CASCADE;

ALTER TABLE submissions_new DROP CONSTRAINT fk_editor_edited_voyage_submissions_new_to_submissions_edited_v;
ALTER TABLE submissions_new ADD CONSTRAINT fk_editor_edited_voyage_submissions_new_to_submissions_edited_v FOREIGN KEY (editor_edited_voyage_id) REFERENCES submissions_edited_voyages (id)  ON DELETE CASCADE;

ALTER TABLE submissions_new DROP CONSTRAINT fk_new_edited_voyage_submissions_new_to_submissions_edited_voya;
ALTER TABLE submissions_new ADD CONSTRAINT fk_new_edited_voyage_submissions_new_to_submissions_edited_voya FOREIGN KEY (new_edited_voyage_id) REFERENCES submissions_edited_voyages (id)  ON DELETE CASCADE;

ALTER TABLE submissions_merge DROP CONSTRAINT fk_editor_edited_voyage_submissions_merge_to_submissions_edited;
ALTER TABLE submissions_merge ADD CONSTRAINT fk_editor_edited_voyage_submissions_merge_to_submissions_edited FOREIGN KEY (editor_edited_voyage_id) REFERENCES submissions_edited_voyages (id) ON DELETE CASCADE;

ALTER TABLE submissions_merge DROP CONSTRAINT fk_proposed_edited_voyage_submissions_merge_to_submissions_edit;
ALTER TABLE submissions_merge ADD CONSTRAINT fk_proposed_edited_voyage_submissions_merge_to_submissions_edit FOREIGN KEY (proposed_edited_voyage_id) REFERENCES submissions_edited_voyages (id) ON DELETE CASCADE;

ALTER TABLE submissions_merge_voyages DROP CONSTRAINT fk_edited_voyage_submissions_merge_voyages_to_submissions_edite;
ALTER TABLE submissions_merge_voyages ADD CONSTRAINT fk_edited_voyage_submissions_merge_voyages_to_submissions_edite FOREIGN KEY (edited_voyage_id) REFERENCES submissions_edited_voyages (id) ON DELETE CASCADE;

ALTER TABLE submissions_attribute_notes DROP CONSTRAINT fk_submissions_merge_notes_to_submissions_edited_voyages;
ALTER TABLE submissions_attribute_notes ADD CONSTRAINT fk_submissions_merge_notes_to_submissions_edited_voyages FOREIGN KEY (edited_voyage_id) REFERENCES submissions_edited_voyages (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE;


ALTER TABLE submissions_attribute_notes DROP CONSTRAINT fk_submissions_attribute_notes_to_submissions_edited_voyage;
ALTER TABLE submissions_attribute_notes ADD CONSTRAINT fk_submissions_attribute_notes_to_submissions_edited_voyage FOREIGN KEY (edited_voyage_id) REFERENCES submissions_edited_voyages (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE submission_editors DROP CONSTRAINT fk_submission_users_to_edited_voyages;
ALTER TABLE submission_editors
  ADD CONSTRAINT fk_submission_users_to_edited_voyages FOREIGN KEY (edited_voyage_id)
      REFERENCES submissions_edited_voyages (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE submissions_sources
  DROP CONSTRAINT fk_submissions_sources_to_submissions;
      
ALTER TABLE submissions_sources
  ADD CONSTRAINT fk_submissions_sources_to_submissions FOREIGN KEY (submission_id)
      REFERENCES submissions (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE submissions_sources_primary
  DROP CONSTRAINT fk_submissions_sources_primaryto_sources;

ALTER TABLE submissions_sources_primary
  ADD CONSTRAINT fk_submissions_sources_primaryto_sources FOREIGN KEY (source_id)
      REFERENCES submissions_sources (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;


ALTER TABLE submissions_sources_books
  DROP CONSTRAINT fk_submissions_sources_books_to_sources;
  
ALTER TABLE submissions_sources_books
  ADD CONSTRAINT fk_submissions_sources_books_to_sources FOREIGN KEY (source_id)
      REFERENCES submissions_sources (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;



ALTER TABLE submissions_sources_others
  DROP CONSTRAINT fk_submissions_sources_othersto_sources;

ALTER TABLE submissions_sources_others
  ADD CONSTRAINT fk_submissions_sources_othersto_sources FOREIGN KEY (source_id)
      REFERENCES submissions_sources (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;


ALTER TABLE submissions_sources_papers
  DROP CONSTRAINT fk_submissions_sources_papers_to_sources;

ALTER TABLE submissions_sources_papers
  ADD CONSTRAINT fk_submissions_sources_papers_to_sources FOREIGN KEY (source_id)
      REFERENCES submissions_sources (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

