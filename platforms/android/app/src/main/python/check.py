import pandas as pd
from com.chaquo.python import Python
import pickle
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.naive_bayes import MultinomialNB
from os.path import dirname, join

def main(text):
  dir = str(Python.getPlatform().getApplication().getFilesDir())

  filename = join(dirname(dir),"model.pickle")
  with open(filename,'rb') as fin:
    clf = pickle.load(fin)

  filename2 = join(dirname(dir),"count_vect.pickle")
  with open(filename2,'rb') as fin2:
    count_vect = pickle.load(fin2)

  check=clf.predict(count_vect.transform([text]))
  check=str(check)
  if check == "[0]":
    return "지인사칭이 의심됩니다."
  elif check=="[1]":
    return "기관사칭이 의심됩니다."
  elif check=="[2]":
    return "택배사기가 의심됩니다."
  else:
    return "피싱문자가 아닙니다."

