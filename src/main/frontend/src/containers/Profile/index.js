import React, { useState } from "react";
import { connect } from "react-redux";
import { addGenrePreference } from "../../store/actions";
import { addLanguagePreference } from "../../store/actions";
import { addCompanyPreference } from "../../store/actions";
import { deleteGenrePreference } from "../../store/actions";
import { deleteLanguagePreference } from "../../store/actions";
import { deleteCompanyPreference } from "../../store/actions";
import "./index.scss";
import { WithContext as ReactTags } from "react-tag-input";
import texts from "../../texts.json";

const ProfilePage = ({
  user,
  suggestions,
  addGenrePreference,
  addLanguagePreference,
  addCompanyPreference,
  deleteGenrePreference,
  deleteLanguagePreference,
  deleteCompanyPreference
}) => {
  const delimiters = [];
  const { genre, language, company } = user.preferences;
  const [dropDown, setDropDownState] = useState({
    genre: false,
    language: false,
    company: false
  });

  const handleGenreAddition = tag => {
    addGenrePreference(tag);
  };

  const handleLanguageAddition = tag => {
    addLanguagePreference(tag);
  };

  const handleCompanyAddition = tag => {
    addCompanyPreference(tag);
  };

  const handleGenreDeletion = index => {
    deleteGenrePreference(genre[index]);
  };

  const handleLanguageDeletion = index => {
    deleteLanguagePreference(language[index]);
  };

  const handleCompanyDeletion = index => {
    deleteCompanyPreference(company[index]);
  };

  const shouldRenderGenreSuggestions = query => {
    return dropDown.genre;
  };

  const shouldRenderLanguageSuggestions = query => {
    return dropDown.language;
  };

  const shouldRenderCompanySuggestions = query => {
    return dropDown.company;
  };

  const handleGenreInputFocus = inputText => {
    let dropDownStateCopy = {
      genre: true,
      language: false,
      company: false
    };
    setDropDownState(dropDownStateCopy);
  };

  const handleLanguageInputFocus = inputText => {
    let dropDownStateCopy = {
      genre: false,
      language: true,
      company: false
    };
    setDropDownState(dropDownStateCopy);
  };

  const handleCompanyInputFocus = inputText => {
    let dropDownStateCopy = {
      genre: false,
      language: false,
      company: true
    };
    setDropDownState(dropDownStateCopy);
  };

  const handleInputBlur = () => {
    let dropDownStateCopy = {
      genre: false,
      language: false,
      company: false
    };
    setDropDownState(dropDownStateCopy);
  };

  return (
    <div className="profilepage-container">
      <h1 className="page-title">{texts.profileHeader}</h1>
      <img src={user.picture} alt={texts.profileHeader} />
      <h3 className="page-title">{user.name}</h3>
      <p className="page-title">{user.email}</p>
      <h3 className="page-title">{texts.preferences}</h3>
      <p className="page-title">{texts.genre}:</p>
      <ReactTags
        tags={genre}
        placeholder={texts.selectGenre}
        allowDragDrop={false}
        suggestions={suggestions.genre}
        handleDelete={handleGenreDeletion}
        handleAddition={handleGenreAddition}
        handleInputFocus={handleGenreInputFocus}
        handleInputBlur={handleInputBlur}
        shouldRenderSuggestions={shouldRenderGenreSuggestions}
        delimiters={delimiters}
      />
      <p className="page-title">{texts.language}:</p>
      <ReactTags
        tags={language}
        placeholder={texts.selectLanguage}
        allowDragDrop={false}
        suggestions={suggestions.language}
        handleDelete={handleLanguageDeletion}
        handleAddition={handleLanguageAddition}
        handleInputFocus={handleLanguageInputFocus}
        handleInputBlur={handleInputBlur}
        shouldRenderSuggestions={shouldRenderLanguageSuggestions}
        delimiters={delimiters}
      />
      <p className="page-title">{texts.productionCompanies}:</p>
      <ReactTags
        tags={company}
        placeholder={texts.selectCompany}
        allowDragDrop={false}
        suggestions={suggestions.company}
        handleDelete={handleCompanyDeletion}
        handleAddition={handleCompanyAddition}
        handleInputFocus={handleCompanyInputFocus}
        handleInputBlur={handleInputBlur}
        shouldRenderSuggestions={shouldRenderCompanySuggestions}
        delimiters={delimiters}
      />
    </div>
  );
};

const mapStateToProps = ({ user, suggestions }) => ({
  user,
  suggestions
});

const mapDispatchToProps = dispatch => ({
  addGenrePreference: payload => dispatch(addGenrePreference(payload)),
  addLanguagePreference: payload => dispatch(addLanguagePreference(payload)),
  addCompanyPreference: payload => dispatch(addCompanyPreference(payload)),
  deleteGenrePreference: payload => dispatch(deleteGenrePreference(payload)),
  deleteLanguagePreference: payload =>
    dispatch(deleteLanguagePreference(payload)),
  deleteCompanyPreference: payload => dispatch(deleteCompanyPreference(payload))
});

export { ProfilePage };

export default connect(mapStateToProps, mapDispatchToProps)(ProfilePage);
