import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import { addPreference } from "../../store/actions";
import "./index.scss";
import { WithContext as ReactTags } from "react-tag-input";
import texts from "../../texts.json";

const ProfilePage = ({ user, suggestions, addPreference }) => {
  const delimiters = [];
  const { genre, language, company } = user.preferences;
  const [showDropDown, setShowDropDown] = useState(false);

  useEffect(() => {
    //const fetchUserDetails = () => {
    //}
    // fetchUserDetails();
  }, []);

  const handleDelete = i => {
    const { tags } = this.tags;

    this.tags = tags.filter((tag, index) => index !== i);
    // this.setState({
    //  tags: tags.filter((tag, index) => index !== i),
    // });
  };

  const handleAddition = tag => {
    addPreference(tag);
  };

  const shouldRenderSuggestions = query => {
    if (query.length >= 0) {
      return showDropDown;
    }
  };

  function handleInputFocus() {
    setShowDropDown(true);
  }

  return (
    <div className="profilepage-container">
      <header className="App-header">
        <h1 className="page-title">Profile page</h1>
        <img
          src={
            "https://timesofindia.indiatimes.com/thumb/msid-69902898,imgsize-115506,width-800,height-600,resizemode-4/69902898.jpg"
          }
          alt="Your profile pic"
        />
        <h3 className="page-title">"~~NAME WILL BE HERE~~"</h3>
        <p className="page-title">"~~EMAIL WILL BE HERE~~"</p>
        <h3 className="page-title">{texts.preferences}</h3>
        <p className="page-title">{texts.genre}:</p>
        <ReactTags
          tags={genre}
          placeholder="Select genre"
          allowDragDrop={false}
          suggestions={suggestions}
          handleDelete={handleDelete}
          handleAddition={handleAddition}
          handleInputFocus={handleInputFocus}
          shouldRenderSuggestions={shouldRenderSuggestions}
          delimiters={delimiters}
        />
        <p className="page-title">{texts.language}:</p>
        <ReactTags
          tags={language}
          placeholder="Select Language"
          allowDragDrop={false}
          suggestions={suggestions}
          handleDelete={handleDelete}
          handleAddition={handleAddition}
          handleInputFocus={handleInputFocus}
          shouldRenderSuggestions={shouldRenderSuggestions}
          delimiters={delimiters}
        />
        <p className="page-title">{texts.productionCompanies}:</p>
        <ReactTags
          tags={company}
          placeholder="Select Company"
          allowDragDrop={false}
          suggestions={suggestions}
          handleDelete={handleDelete}
          handleAddition={handleAddition}
          handleInputFocus={handleInputFocus}
          shouldRenderSuggestions={shouldRenderSuggestions}
          delimiters={delimiters}
        />
      </header>
    </div>
  );
};

const mapStateToProps = ({ user, suggestions }) => ({
  user,
  suggestions
});

const mapDispatchToProps = dispatch => ({
  addPreference: payload => dispatch(addPreference(payload))
});

export { ProfilePage };

export default connect(mapStateToProps, mapDispatchToProps)(ProfilePage);
